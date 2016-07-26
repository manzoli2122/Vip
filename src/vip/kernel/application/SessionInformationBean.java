package vip.kernel.application;

import java.security.Principal;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import vip.core.domain.User;
import vip.core.domain.UserType;
import vip.core.persistence.UserDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;




@SessionScoped
@Stateful
public class SessionInformationBean implements SessionInformation {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private UserDAO userDAO;

	@Resource 	
	private SessionContext sessionC;
	
	@EJB    	
	private CoreInformation coreInformation;
	
	private User currentUser;

	
	
	@Override
	public User getCurrentUser() {
		if( currentUser == null ){
			Principal principal = sessionC.getCallerPrincipal();
			if(principal != null){
				if(principal.getName()!=null){
					try { 
						currentUser = userDAO.retrieveByEmail(principal.getName());
					} 
					catch (PersistentObjectNotFoundException | MultiplePersistentObjectsFoundException e) {
						currentUser = null ;
					}
				}
			}
		}
		return currentUser;
	}



	@Override
	public boolean isAdmin() {
		if(getCurrentUser()!=null){
			if(currentUser.getUserTypes().contains(UserType.Admin)){
				return true;
			}
		}
		return false;
	}

	
	
	@Override
	public boolean isSuperUsuario(){
		try {
			if(coreInformation.getCurrentConfig().getAdministrador().equals(getCurrentUser())){
				return true;
			}
		}
		catch(Exception e){	}
		return false;
	}
	
	
	@Override
	public boolean isGerente(){
		try {
			if(coreInformation.getCurrentConfig().getGerente().equals(getCurrentUser())){
				return true;
			}
		}
		catch(Exception e){	}
		return false;
	}


	@Override
	public boolean isEmployee() {
		if(getCurrentUser()!=null){
			if(currentUser.getUserTypes().contains(UserType.Employee)){
				return true;
			}
		}
		return false;
	}


	
}
