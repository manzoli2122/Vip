package vip.core.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudValidationError;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.MultipleChoiceFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import vip.core.application.ManageSalaryService;
import vip.core.application.SessionInformation;
import vip.core.domain.AdvanceMoney;
import vip.core.domain.Salary;
import vip.core.domain.User;
import vip.core.persistence.AdvanceMoneyDAO;
import vip.secretariat.domain.EmployeeAttendance;
import vip.secretariat.persistence.EmployeeAttendanceDAO;



@Named
@SessionScoped
public class ManageSalaryController extends CrudController<Salary>{

	private static final long serialVersionUID = 1L;

	@EJB
	private ManageSalaryService manageSalarioService;
	
	@EJB
	private SessionInformation sessionInformation;

	@EJB
	private EmployeeAttendanceDAO atendimento_funcionarioDAO;
	
	@EJB
	private AdvanceMoneyDAO valeDAO;
	
	@Inject
	private CoreController coreController;
	
	
	
	public void buscarServicos(){
		if(selectedEntity.getFuncionario()==null)return;
		
		List<EmployeeAttendance> list = atendimento_funcionarioDAO.retrieveSalario(selectedEntity.getFuncionario());
		selectedEntity.setServicos(new TreeSet<EmployeeAttendance>(list));
		
		List<AdvanceMoney> vales = valeDAO.retrieveSalario(selectedEntity.getFuncionario());
		selectedEntity.setVales(new TreeSet<AdvanceMoney>(vales));
		
		selectedEntity.calcularValorSalario();
	}
	
	
	
	
	/*   CONSTRUTOR DA CLASSE */
	public ManageSalaryController(){
		 viewPath = "/secretariat/manageSalario/";
	     bundleName = "msgsSalao";
	}
	
	/* METODO OBRIGATORIO */
	@Override
	protected CrudService<Salary> getCrudService() {
		return manageSalarioService;
	}

	/* METODO OBRIGATORIO */
	@Override
	protected Salary createNewEntity() {
		Salary salario = new Salary();
		salario.setCreateDate(Calendar.getInstance());
		salario.setCreateRegister(sessionInformation.getCurrentUser());
		return salario;
	}
	
	/* METODO OBRIGATORIO */
	@Override
	protected void initFilters() {
		addFilter(
				new MultipleChoiceFilter<User>("manageSalario.filter.byFuncionario", "funcionario", "Por Funcionario" ,getFuncionarios() ,getMap()));
			
	}
	
	
	@Override
	public String list() {
		cancelDeletion();
		return super.list();
	}
	
	@Override
	public void cancelDeletion() {
		trashCan.clear();
	}
	
	

	/*	FUNCOES PARA O FILTRO */
	private Map<String, String> getMap(){
		Map<String, String> map1 = new LinkedHashMap<String, String>() ;
		User user;
		Iterator<User> iter = getFuncionarios().iterator();
		while(iter.hasNext()){
			user = iter.next();
			map1.put(user.getId().toString(), user.getName());
		}
		return map1;
	}
	
	
	/*	FUNCOES PARA O FILTRO */
	private List<User> getFuncionarios(){
		return coreController.getEmployees();
	}
	
	
	
	
	@Override
	protected void prepEntity() {
		selectedEntity.calcularValorSalario();
	}
	
	private void sendEmailInfo(){
		manageSalarioService.sendEmailInfo(selectedEntity);
	}
	
	
	
	
	
	
	
	@Override
	public String save() {
		prepEntity();
		
		try {
			if (selectedEntity.getId() == null) {
				getCrudService().validateCreate(selectedEntity);
				getCrudService().create(selectedEntity);
				addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_INFO, getBundlePrefix() + ".text.createSucceeded", summarizeSelectedEntity());
			}
			else {
				getCrudService().validateUpdate(selectedEntity);
				getCrudService().update(selectedEntity);
				addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_INFO, getBundlePrefix() + ".text.updateSucceeded", summarizeSelectedEntity());
			}
		}
		catch (CrudException crudException) {
			for (CrudValidationError error : crudException) {
				if (error.getFieldName() != null) addFieldI18nMessage(getFieldName(error.getFieldName()), getBundleName(), FacesMessage.SEVERITY_ERROR, error.getMessageKey(), error.getMessageParams());
				else addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_ERROR, error.getMessageKey(), error.getMessageParams());
			}
			return null;
		}

		Iterator<EmployeeAttendance> ite = selectedEntity.getServicos().iterator();
		EmployeeAttendance serv;
		while(ite.hasNext()){
			serv = ite.next();
			serv = atendimento_funcionarioDAO.merge(serv);
			serv.setSalary(selectedEntity);
			atendimento_funcionarioDAO.save(serv);					
		}
		
		
		Iterator<AdvanceMoney> ite1 = selectedEntity.getVales().iterator();
		AdvanceMoney val;
		while(ite1.hasNext()){
			val = ite1.next();
			val = valeDAO.merge(val);
			val.setSalary(selectedEntity);
			valeDAO.save(val);
		}
		
				
		sendEmailInfo();
		return list();
	}
	
	
	
	@Override
	public String delete() {
		
		List<Object> notDeleted = new ArrayList<Object>();

		for (Salary entity : trashCan)
			try {
				Iterator<EmployeeAttendance> ite = entity.getServicos().iterator();
				EmployeeAttendance serv;
				while(ite.hasNext()){
					serv = ite.next();
					serv = atendimento_funcionarioDAO.merge(serv);
					serv.setSalary(null);
					atendimento_funcionarioDAO.save(serv);					
				}
				Iterator<AdvanceMoney> ite1 = entity.getVales().iterator();
				AdvanceMoney val;
				while(ite1.hasNext()){
					val = ite1.next();
					val = valeDAO.merge(val);
					val.setSalary(null);
					valeDAO.save(val);
				}
				getCrudService().validateDelete(entity);
				getCrudService().delete(entity);
			}
			catch (CrudException crudException) {
				for (CrudValidationError error : crudException)
					addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_ERROR, error.getMessageKey(), error.getMessageParams());

				notDeleted.add(entity);
			}

		trashCan.removeAll(notDeleted);
		if (!trashCan.isEmpty()) {
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_INFO, getBundlePrefix() + ".text.deleteSucceeded", listTrash());
			trashCan.clear();
		}

		return list();

	}
	
	
}
