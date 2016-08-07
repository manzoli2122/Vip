package vip.core.controller;

import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.MultipleChoiceFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import vip.core.application.ManageSalaryService;
import vip.core.domain.Salary;
import vip.core.domain.User;
import vip.kernel.application.SessionInformation;


@Named
@SessionScoped
public class ManageSalaryController extends CrudController<Salary>{

	private static final long serialVersionUID = 1L;

	@EJB
	private ManageSalaryService manageSalaryService;
	
	@EJB
	private SessionInformation sessionInformation;
	
	@Inject
	private CoreController coreController;
	
	
	
	
	/*   CONSTRUTOR DA CLASSE */
	public ManageSalaryController(){
		 viewPath = "/core/manageSalary/";
	     bundleName = "msgsCore";
	}
	
	
	/* METODO OBRIGATORIO */
	@Override
	protected CrudService<Salary> getCrudService() {
		return manageSalaryService;
	}

	
	/* METODO OBRIGATORIO */
	@Override
	protected Salary createNewEntity() {
		Salary salario = new Salary();
		salario.setCreateDate(Calendar.getInstance());
		return salario;
	}
	
	
	
	/* METODO OBRIGATORIO */
	@Override
	protected void initFilters() {
		addFilter(	new MultipleChoiceFilter<User>("manageSalario.filter.byFuncionario", "funcionario", "Por Funcionario" ,getFuncionarios() ,getMap()));
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
	
	
	
	
	
	public Double calcularServicos(){
		return manageSalaryService.calcularServicos(selectedEntity);
	}
	
	
	public Double calcularVales(){
		return manageSalaryService.calcularVales(selectedEntity);
	}
	
	
	
	public void buscarServicos(){
		manageSalaryService.buscarServicos(selectedEntity);
	}
	
	
	public String sendEmailInfoFuncionario(){
		try{
			manageSalaryService.sendEmailInfo(selectedEntity, selectedEntity.getFuncionario().getEmail());
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_ERROR, getBundlePrefix() + ".email.funcionario" , summarizeSelectedEntity()  );
			
		}catch(Exception e){
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_ERROR, getBundlePrefix() + ".error.email.funcionario" , summarizeSelectedEntity()  );
		}
		
		return null;
	}
	
	
	
	
	
	
	
	/** @see br.ufes.inf.nemo.util.ejb3.controller.CrudController#save() */
	@Override
	public String save() {
		try{
			return super.save();
		}
		catch(Exception e){
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_ERROR, getBundlePrefix() + ".error.save" , summarizeSelectedEntity()  );
			return null;
		}
	}
	
	
	
	
	/** @see br.ufes.inf.nemo.util.ejb3.controller.CrudController#delete() */
	@Override
	public String delete() {
		try{
			return super.delete();
		}
		catch(Exception e){
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_ERROR, getBundlePrefix() + ".error.delete", summarizeSelectedEntity());
			cancelDeletion();
			return null;
		}
	}
	
	
}
