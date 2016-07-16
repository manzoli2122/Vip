package vip.core.application;

import java.io.Serializable;

import javax.ejb.Local;

import vip.core.domain.Academic;
import vip.core.exceptions.LoginFailedException;

/**
 * Local EJB interface for the session information component. This bean is responsible for storing information on each
 * different user of the system, such as the Academic object that represents the logged in user. It should also provide
 * an authentication method for the controller, identifying users of the system.
 * 
 * @author Vitor E. Silva Souza (vitorsouza@gmail.com)
 */
@Local
public interface SessionInformation extends Serializable {
	
	/**
	 * Obtains the currently logged in user.
	 * 
	 * @return The Academic object that represents the user that is currently logged in.
	 */
	Academic getCurrentUser();
	
	
	boolean isAdmin();
	
	

	

	
	//void login(String username, String password) throws LoginFailedException;
}
