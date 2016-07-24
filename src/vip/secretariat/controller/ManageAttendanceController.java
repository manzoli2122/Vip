package vip.secretariat.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.MultipleChoiceFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import vip.core.application.CoreInformation;
import vip.core.application.SessionInformation;
import vip.core.domain.User;
import vip.core.persistence.UserDAO;
import vip.secretariat.application.ManageAttendanceService;
import vip.secretariat.domain.Attendance;
import vip.secretariat.domain.EmployeeAttendance;
import vip.secretariat.domain.Payment;
import vip.secretariat.domain.FormOfPayment;


@Named
@SessionScoped
public class ManageAttendanceController extends CrudController<Attendance> {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManageAttendanceService manageAttendanceService;
	
	@EJB
	private SessionInformation sessionInformation;
	
	@EJB
	private CoreInformation coreInformation;
	
	@EJB 
	private UserDAO userDAO;
	
	
	
	private EmployeeAttendance employeeAttendance;
		
	private Payment payment;
		
	
	
	
	
	
	/* METODO OBRIGATORIO */
	@Override
	protected void initFilters() {
		addFilter(new MultipleChoiceFilter<User>("manageUser.filter.byCliente", "cliente", "Por Cliente" ,getLista() ,getMap()));
	}
	/*	FUNCOES PARA O FILTRO */
	private Map<String, String> getMap(){
		Map<String, String> map1 = new LinkedHashMap<String, String>() ;
		User user;
		Iterator<User> iter = getLista().iterator();
		while(iter.hasNext()){
			user = iter.next();
			map1.put(user.getId().toString(), user.getName());
		}
		return map1;
	}
	/*	FUNCOES PARA O FILTRO */
	private List<User> getLista(){
		List<User> lista1;
		lista1 = userDAO.retrieveAll();
		return lista1;
	}
	
	
	
	
	/*   CONSTRUTOR DA CLASSE */
	public ManageAttendanceController(){
		 viewPath = "/secretariat/manageAttendance/";
	     bundleName = "msgsCore";
	}

	
	
	
	public String voltarFormulario(){
		employeeAttendance =null;
		payment = null;
		return getViewPath() + "form.xhtml?faces-redirect=" + getFacesRedirect();
	}
	

	
	/* METODO OBRIGATORIO */
	@Override
	protected CrudService<Attendance> getCrudService() {
		return manageAttendanceService;
	}

	
	
	
	
	
	
	
	
	
	
	/* METODO OBRIGATORIO */
	@Override
	protected Attendance createNewEntity() {
		employeeAttendance = null;
		payment = null;
				
		Attendance newEntity = new Attendance();
		newEntity.setCreateDate(Calendar.getInstance());
		newEntity.setValor(0.0);
		
		newEntity.setEmployeeAttendances(new ArrayList<EmployeeAttendance>());
		newEntity.setPayments(new ArrayList<Payment>());
	
		return newEntity;
	}

	
	/* CHECA O ATENDIMENTO QUANDO BUSCA NO BANCO */
	@Override
	protected void checkSelectedEntity() {
				
		employeeAttendance =null;
		payment = null;
		
		if (selectedEntity.getEmployeeAttendances() == null)
			selectedEntity.setEmployeeAttendances(new ArrayList<EmployeeAttendance>());
		payment = null;
		
		if (selectedEntity.getPayments() == null)
			selectedEntity.setPayments(new ArrayList<Payment>());
	}

	
	/* PREPARA UM ATENDIMENTO ANTES DE SALVA-LO */
	@Override
	protected void prepEntity() {
		//selectedEntity.setEmployeeAttendance(employeeAttendances);
		//selectedEntity.setPayments(payments);
		//selectedEntity.setValor(selectedEntity.calcularValorServicos());
	}
	
	
	
	
	
	
	
	/** @see br.ufes.inf.nemo.util.ejb3.controller.CrudController#save() */
	@Override
	public String save() {
		try{
			return super.save();
		}
		catch(Exception e){
			selectedEntity.setId(null);
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

	
	
	
	
	
	
	public boolean isAlteravel(){
		return manageAttendanceService.isAlteravel(selectedEntity);
	}
	
	
	
	public void calcula_Porcentagem(){
		
		if(FormOfPayment.Cartao_Credito.equals(payment.getFormOfPayment())){
			payment.setPerc_cartao(coreInformation.getPerc_Credito());
		}
		else if(FormOfPayment.Cartao_Debito.equals(payment.getFormOfPayment())){
			payment.setPerc_cartao(coreInformation.getPerc_Debito());
		}
		
	}
	


	
	
	
	
	
	
	/* FUNCOES PARA SERVICO  */
	
	public String createNewService(){
		employeeAttendance = new EmployeeAttendance(); 
		employeeAttendance.setAttendance(selectedEntity);
		employeeAttendance.setEmployee(sessionInformation.getCurrentUser());
		employeeAttendance.setDesconto(0.0);
		return getViewPath() + "servicos.xhtml?faces-redirect=" + getFacesRedirect();
	}
		
	public String excluirServico(){
		selectedEntity.getEmployeeAttendances().remove(employeeAttendance);
		employeeAttendance=null;
		selectedEntity.calcularValor();
		return getViewPath() + "form.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	public String saveService(){
		selectedEntity.getEmployeeAttendances().add(employeeAttendance);
		employeeAttendance=null;
		selectedEntity.calcularValor();
		return getViewPath() + "form.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	
	public EmployeeAttendance getEmployeeAttendance() { return employeeAttendance; }
	public void setEmployeeAttendance(EmployeeAttendance servico) { this.employeeAttendance = servico; }
	
	

	
	
	
	
	
	
	/* FUNCOES PARA PaymentS */
	
	public String createNewPagamento(){
		payment = new Payment(); 
		payment.setIncome(selectedEntity);
		payment.setParcelas(1);
		payment.setPerc_cartao(1.0);
		return getViewPath() + "pagamentos.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	public String savePagamento(){
		selectedEntity.getPayments().add(payment);
		payment=null;
		return getViewPath() + "form.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	public String excluirPagamento(){
		selectedEntity.getPayments().remove(payment);
		payment=null;
		return getViewPath() + "form.xhtml?faces-redirect=" + getFacesRedirect();
	}
		
	
	public Payment getPayment() { return payment; }
	public void setPayment(Payment payment) { this.payment = payment; }
	
	
	
}