package vip.core.application;

import java.util.Calendar;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import vip.core.domain.Salary;
import vip.kernel.application.CoreInformation;
import vip.kernel.domain.VipConfiguration;
import vip.secretariat.domain.Attendance;
import vip.secretariat.persistence.AttendanceDAO;

@Stateless
@DeclareRoles({ "Admin" , "Employee" , "Cliente" })
@RolesAllowed({ "Admin" })
public class RelatorioDiarioServiceBean implements RelatorioDiarioService{

	private static final long serialVersionUID = 1L;

	@EJB 
	private CoreInformation	coreInformation;
	
	@EJB
	private AttendanceDAO  attendanceDAO ;
	
	@EJB
	private RelatorioPdfService relatorioPdfService; 
	
	
	@Override
	public List<Attendance> buscarByDate(Calendar inicio, Calendar fim) {
		return attendanceDAO.retrieveByDate(inicio, fim);
	}
	
	@Override
	public void salvarRelatorioDiario(List<Attendance> lista) {
		
		MultiPartEmail email;
		VipConfiguration config = coreInformation.getCurrentConfig(); 
		String msg = "Relatorio do dia : "  + lista.get(0).getCreateDate().getTime().toLocaleString().substring(0,10);
		
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(relatorioPdfService.RelatorioDiario(lista));  
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Salário no Salao");
		attachment.setName("salario.pdf");
		
		
		try{
			 email = new MultiPartEmail();
			 email.setHostName(config.getSmtpServerAddress());
			 email.setSmtpPort(config.getSmtpServerPort());
			 email.setAuthenticator(new DefaultAuthenticator(config.getSmtpUsername(), config.getSmtpPassword()));
			 email.setTLS(true);
			 email.setFrom(config.getSmtpUsername());
			 email.setSubject("Salao Espaço Vip - Relatório " + lista.get(0).getCreateDate().getTime().toLocaleString().substring(0,10));
			 email.setMsg(msg);
			 email.addTo(config.getSmtpUsername());
			 email.attach(attachment);
			 email.send();
		
		}
		catch (EmailException e) {
			e.printStackTrace();
		}	
		
		
		
		
	}
	
	
	

}
