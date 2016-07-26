package vip.core.application;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudOperation;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import vip.core.domain.AdvanceMoney;
import vip.core.domain.Salary;
import vip.core.persistence.AdvanceMoneyDAO;
import vip.core.persistence.SalaryDAO;
import vip.core.persistence.VipConfigurationDAO;
import vip.kernel.application.SessionInformation;
import vip.kernel.domain.VipConfiguration;
import vip.secretariat.domain.EmployeeAttendance;
import vip.secretariat.persistence.EmployeeAttendanceDAO;




@Stateless
@DeclareRoles({ "Admin" , "Employee" , "Cliente"})
@RolesAllowed({ "Admin" })
public class ManageSalaryServiceBean  extends CrudServiceBean<Salary> implements ManageSalaryService{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private SalaryDAO salarioDAO;
	
	@EJB 
	private SessionInformation sessionInformation;
	
	@EJB
	private VipConfigurationDAO vipDAO;
	
	@EJB
	private EmployeeAttendanceDAO employeeAttendanceDAO;
	
	@EJB
	private AdvanceMoneyDAO valeDAO;
	
	//@EJB 
	//private RelatorioPdf relatorioPdf;
	
	
	@Override
	public BaseDAO<Salary> getDAO() {
		return salarioDAO;
	}


	
	@Override
	public Double calcularServicos(Salary salary){
		Double valor = 0.0;
			
		if((salary != null) && salary.getServicos()!=null) {
			Iterator<EmployeeAttendance> ite = salary.getServicos().iterator();
			while (ite.hasNext()) {
				valor += ite.next().getComissao();			
			}
		}
		return valor;
	}
	
	
	@Override
	public Double calcularVales(Salary salary){
		Double valor = 0.0;
		if((salary != null) && salary.getVales() != null){
			Iterator<AdvanceMoney> it = salary.getVales().iterator();
			while (it.hasNext()) {
				valor += it.next().getValor();			
			}
		}
		return valor;
	}
	
	
	
	
	public void calcularValorSalario(Salary salary){
		
		Double valor = calcularServicos(salary) - calcularVales(salary);
		salary.setValor(valor);
	
	}
	
	
	
	@Override
	public void validateCreate(Salary entity) throws CrudException {
		
		if(!sessionInformation.isSuperUsuario() && !sessionInformation.isGerente() ){
			int i = 9/0;
		}
		
		entity.setCreateRegister(sessionInformation.getCurrentUser());
		entity.setLastUpdateRegister(sessionInformation.getCurrentUser());
			
		Calendar hoje = Calendar.getInstance();
		entity.setLastUpdateDate(hoje);
		
		calcularValorSalario(entity);
		
		
	}
	
	
	
	
	
	
	
	
	@Override
	public void create(Salary entity) {
		entity = validate(entity, null);

		Iterator<EmployeeAttendance> ite = entity.getServicos().iterator();
		EmployeeAttendance serv;
		while(ite.hasNext()){
			serv = ite.next();
			serv = employeeAttendanceDAO.merge(serv);
			serv.setSalary(entity);					
		}
		
		Iterator<AdvanceMoney> ite1 = entity.getVales().iterator();
		AdvanceMoney val;
		while(ite1.hasNext()){
			val = ite1.next();
			val = valeDAO.merge(val);
			val.setSalary(entity);
		}
		
		
		log(CrudOperation.CREATE, entity);
		getDAO().save(entity);
	}
	
	
	
	@Override
	public void validateDelete(Salary entity) throws CrudException {
		
		if(!sessionInformation.isSuperUsuario() && !sessionInformation.isGerente() ){
			int i = 9/0;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar hoje = Calendar.getInstance();
		
		String dataAtualFormatada = sdf.format(hoje.getTime());
		String dataEventoFormatada = sdf.format(entity.getCreateDate().getTime());
			
		if(!dataEventoFormatada.equals(dataAtualFormatada)){
			int i = 9/0;
		}
		
		
	}
	
	
	
	@Override
	public void delete(Salary entity) {
		entity = getDAO().retrieveById(entity.getId());
		if (entity != null) {
			
			Iterator<EmployeeAttendance> ite = entity.getServicos().iterator();
			EmployeeAttendance serv;
			while(ite.hasNext()){
				serv = ite.next();
				serv = employeeAttendanceDAO.merge(serv);
				serv.setSalary(null);				
			}
			
			Iterator<AdvanceMoney> ite1 = entity.getVales().iterator();
			AdvanceMoney val;
			while(ite1.hasNext()){
				val = ite1.next();
				val = valeDAO.merge(val);
				val.setSalary(null);
			}
			
			// Deletes the entity.
			getDAO().delete(entity);
			log(CrudOperation.DELETE, entity);
		}
	}
	
	
	
	@Override
	public void validateUpdate(Salary entity) throws CrudException {
		
		int i = 9/0;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void buscarServicos(Salary selectedEntity){
		if(selectedEntity.getFuncionario()==null)return;
		
		List<EmployeeAttendance> list = employeeAttendanceDAO.retrieveServicoAberto(selectedEntity.getFuncionario());
		selectedEntity.setServicos(new TreeSet<EmployeeAttendance>(list));
		
		List<AdvanceMoney> vales = valeDAO.retrieveValesAbertos(selectedEntity.getFuncionario());
		selectedEntity.setVales(new TreeSet<AdvanceMoney>(vales));
		
		calcularValorSalario(selectedEntity);
		
		
	}
	
	
	@Override
	protected void log(CrudOperation operation, Salary entity) {
		String mesagem = "MANAGE_SALARIO → "+ operation.name() + " → " + entity.toString();
		//sessionService.log(mesagem);
	}

	
	@Override
	public void sendEmailInfo(Salary salario){
		
		String emailAddress = salario.getFuncionario().getEmail();
		emailAddress = "manzoli2122@gmail.com";
		
		VipConfiguration config = null;
		try {	config = vipDAO.retrieveCurrentConfiguration(); }
		catch (PersistentObjectNotFoundException e) {return; }
		
		String msg = "Serviços \n\n";
		
		EmailAttachment attachment = new EmailAttachment();
		//attachment.setPath(relatorioPdf.RelatorioSalario(salario));  
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Salário no Salao");
		attachment.setName("salario.pdf");

		 try{
			 // Create the email message
			 MultiPartEmail email = new MultiPartEmail();
		  
			 email.setHostName(config.getSmtpServerAddress());
			 email.setSmtpPort(config.getSmtpServerPort());
			 email.setAuthenticator(new DefaultAuthenticator(config.getSmtpUsername(), config.getSmtpPassword()));
			 email.setSSL(true);
			 email.setFrom(config.getSmtpUsername());
			// email.setSubject("Salario "+ salario.getFuncionario().getName()+" "+ salario.getCreateDate().toLocaleString().substring(0,10)+" "+salario.getId());
			 email.setSubject("Salario "+ salario.getFuncionario().getName()+" "+ salario.getCreateDate().getTime().toString().substring(0,10)+" "+salario.getId());
			 email.setMsg(msg);
			 email.addTo(emailAddress);
			 email.addTo(config.getSmtpUsername());
			 email.attach(attachment);
			 email.send();
		  
		}
		catch (EmailException e) {
			e.printStackTrace();
		}
		 		
	}
	
	
}
