package vip.core.application;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import vip.core.domain.User;
import vip.core.domain.UserType;
import vip.core.domain.VipConfiguration;
import vip.core.persistence.UserDAO;
import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Stateless
@DeclareRoles({ "Admin" , "Employee" , "Cliente" })
@RolesAllowed({ "Admin" })
public class ManageUsersServiceBean extends CrudServiceBean<User> implements ManageUsersService{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserDAO userDAO;
	
	@EJB 
	private SessionInformation sessionInformation;
	
	@EJB 
	private CoreInformation	coreInformation;
	
	@Override
	public BaseDAO<User> getDAO() {
		return userDAO;
	}
	
	
	@Override
	public void validateCreate(User entity) throws CrudException {
		Calendar now = Calendar.getInstance();
		entity.setLastUpdateDate(now);
		entity.setCreationDate(now);
		sendEmailRegister(entity);
		try {
			entity.setPassword(TextUtils.produceMd5Hash(entity.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void validateUpdate(User entity) throws CrudException {
		entity.setLastUpdateDate(Calendar.getInstance());
	}
	
	
	@Override
	protected User validate(User newEntity, User oldEntity) {
		
		if(oldEntity!= null){
			if(oldEntity.getUserTypes().contains(UserType.Admin)){
				if(!sessionInformation.isSuperUsuario() && !sessionInformation.isGerente()){
						return oldEntity;
				}
			}
			else{
				if(newEntity.getUserTypes().contains(UserType.Admin)){
					if(!sessionInformation.isSuperUsuario() && !sessionInformation.isGerente()){
						newEntity.getUserTypes().remove(UserType.Admin);
				}
				}
			}
			
		}
		else{
			if(newEntity.getUserTypes().contains(UserType.Admin)){
				if(!sessionInformation.isSuperUsuario() && !sessionInformation.isGerente()){
					newEntity.getUserTypes().remove(UserType.Admin);
				}
			}
		}
		
		return super.validate(newEntity, oldEntity);
	}
	
	
	
	
	
	@Override
	public void sendEmailRegister(User entity){
		
		MultiPartEmail email;
		HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		String emailAddress = entity.getEmail();
		emailAddress = "manzoli2122@gmail.com";
		VipConfiguration config = coreInformation.getCurrentConfig(); 
		
		String msg = "Bem Vindo Ao Sistema Marvin, ele agrega ferramentas úteis para o gerenciamento de tarefas de ensino e pesquisa em uma universidade. \n\n"
				+ "para realizar o primeiro acesso entre na pagina \n"
				+ origRequest.getRequestURL().substring(0, origRequest.getRequestURL().indexOf(origRequest.getServletPath())) + "\n"
				+ "para realizar login entra com seu email, sua senha inicial será: \n"
				+ entity.getPassword();
		
		try{
			 email = new MultiPartEmail();
			 email.setHostName(config.getSmtpServerAddress());
			 email.setSmtpPort(config.getSmtpServerPort());
			 email.setAuthenticator(new DefaultAuthenticator(config.getSmtpUsername(), config.getSmtpPassword()));
			 email.setTLS(true);
			 //email.setSSL(true);
			 email.setFrom(config.getSmtpUsername());
			 email.setSubject("Cadastro Sistema do Salao Espaço Vip");
			 email.setMsg(msg);
			 email.addTo(emailAddress);
			 email.addTo(config.getSmtpUsername());
			 email.send();
		
		}
		catch (EmailException e) {
			e.printStackTrace();
		}	
		
		
		
		
	}
	
	
}
