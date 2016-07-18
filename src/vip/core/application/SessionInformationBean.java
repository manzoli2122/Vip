package vip.core.application;

import java.security.Principal;
import java.util.logging.Logger;

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

/**
 * Stateful session bean implementing the session information component. See the implemented interface documentation for
 * details.
 * 
 * @author Vitor E. Silva Souza (vitorsouza@gmail.com)
 * @see br.org.feees.sigme.core.application.SessionInformation
 */
@SessionScoped
@Stateful
public class SessionInformationBean implements SessionInformation {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(SessionInformationBean.class.getCanonicalName());

	
	
	/** The DAO for Academic objects. */
	@EJB
	private UserDAO academicDAO;

	
	@Resource 	
	private SessionContext sessionC;
	
	
	/** The current user logged in. */
	private User currentUser;

	
	
	/** @see br.org.feees.sigme.core.application.SessionInformation#getCurrentUser() */
	@Override
	public User getCurrentUser() {
		if( currentUser == null ){
			Principal principal = sessionC.getCallerPrincipal();
			if(principal != null){
				if(principal.getName()!=null){
					try { 
						currentUser = academicDAO.retrieveByEmail(principal.getName());
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
	public boolean isEmployee() {
		if(getCurrentUser()!=null){
			if(currentUser.getUserTypes().contains(UserType.Employee)){
				return true;
			}
		}
		return false;
	}


	
}
