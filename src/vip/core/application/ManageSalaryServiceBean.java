package vip.core.application;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudOperation;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import vip.core.domain.Salary;
import vip.core.domain.VipConfiguration;
import vip.core.persistence.SalaryDAO;
import vip.core.persistence.VipConfigurationDAO;




@Stateless
@DeclareRoles({ "Admin" , "Employee" })
@RolesAllowed({ "Admin", "Employee" })
public class ManageSalaryServiceBean  extends CrudServiceBean<Salary> implements ManageSalaryService{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private SalaryDAO salarioDAO;
	
	@EJB 
	private SessionInformation sessionInformation;
	
	@EJB
	private VipConfigurationDAO vipDAO;
	
	//@EJB 
	//private RelatorioPdf relatorioPdf;
	
	
	@Override
	public BaseDAO<Salary> getDAO() {
		return salarioDAO;
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
