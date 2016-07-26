package vip.kernel.controller;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import vip.core.domain.User;
import vip.kernel.application.CoreInformation;
import vip.kernel.application.SessionInformation;
import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;

/**
 * Session-scoped managed bean that provides to web pages the login service, indication if the user is logged in and the
 * current date/time.
 * 
 * @author Vitor E. Silva Souza (vitorsouza@gmail.com)
 */
@Named
@SessionScoped
public class SessionController extends JSFController {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(SessionController.class.getCanonicalName());

	/** Information on the whole application. */
	@EJB
	private CoreInformation coreInformation;

	/** Information on the current visitor. */
	@EJB
	private SessionInformation sessionInformation;


	

	
	public boolean isAdmin() {
		return sessionInformation.isAdmin();
	}

	
	public boolean isEmployee() {
		return sessionInformation.isEmployee();
	}
	

	public boolean isSuperUsuario() {
		return sessionInformation.isSuperUsuario();
	}
	
	
	public boolean isGerente() {
		return sessionInformation.isGerente();
	}
	
	

	
	public boolean isLoggedIn() {
		return sessionInformation.getCurrentUser() != null;
	}

	
	public User getCurrentUser() {
		return sessionInformation.getCurrentUser();
	}

	
	
	
	public Date getSessionExpirationTime() {
		Date expTime = null;

		// Attempts to retrieve this information from the external context.
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if (session != null) {
			logger.log(Level.FINEST, "Calculating session expiration time from the HTTP session...");
			long expTimeMillis = session.getLastAccessedTime() + session.getMaxInactiveInterval() * 1000;
			expTime = new Date(expTimeMillis);
		}

		// If it could not be retrieved from the external context, use default of 30 minutes.
		if (expTime == null) {
			logger.log(Level.FINEST, "HTTP Session not available. Using default expiration time: now plus 30 minutes...");
			expTime = new Date(System.currentTimeMillis() + 30 * 60000);
		}

		logger.log(Level.FINEST, "Calculated expiration time: {0}", expTime);
		return expTime;
	}

	
}
