package vip.kernel.application;

import br.ufes.inf.nemo.util.TextUtils;
import vip.core.domain.User;
import vip.core.domain.UserType;
import vip.core.domain.User_;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import java.io.Serializable;


public class LoginService  implements Serializable, LoginModule {

	
	
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(LoginService.class.getCanonicalName());
	
	private User academic;
	
	private Subject subject;
	private CallbackHandler callbackHandler; 
	@SuppressWarnings({ "rawtypes", "unused" })
	private Map sharedState; 
	@SuppressWarnings({ "rawtypes", "unused" })
	private Map options;
	private boolean loginOk;
	private Principal identity;
	private char[] credential;
	
	
	   
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
		this.subject = subject;
	    this.callbackHandler = callbackHandler;
	    this.sharedState = sharedState;
	    this.options = options;	 
	}

	@Override
	public boolean login() throws LoginException {
		loginOk = false;
	    getUsernameAndPassword(); 
	    
	    getAcademic();
	    //logger.log(Level.INFO, "getAcademic() - OK");
	    
	    validateUser(); 
	    //logger.log(Level.INFO, "validateUser() - OK");
	    
	    loginOk = true;
	    return true;
	}

	@Override
	public boolean commit() throws LoginException {
		if( loginOk == false )return false;
		Group callerGroup ;
	    callerGroup = new SimpleGroup("CallerPrincipal");
	    callerGroup.addMember(identity);
		Set<Principal> principals = subject.getPrincipals();
	    principals.add(identity);  
	    principals.add(getRoleSets());
	    principals.add(callerGroup);
	    
	    try {
	    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("vipLogin");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			
			tx.begin();
			User result =  em.find(User.class, academic.getId());
			
			result.setLastLoginDate(Calendar.getInstance());
			em.merge(result);
			
			tx.commit();
			em.close();
			emf.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return true;
	}
	
	
	

	@Override
	public boolean abort() throws LoginException {
		logger.log(Level.INFO, "LOGIN  ABORT - OK");
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		Set<Principal> principals = subject.getPrincipals();
	    principals.remove(identity);
	    principals.clear();
	    if(principals.isEmpty())
	    	logger.log(Level.INFO, "MYLOGIN  LOGOUT OK");
	    return true;
	}
	
	
	
	protected void getUsernameAndPassword() throws LoginException{
	   
		
		if( callbackHandler == null ){ throw new LoginException();  } 
	    NameCallback nc = new NameCallback("username");
	    PasswordCallback pc = new PasswordCallback("password: ", false);
	    Callback[] callbacks = {nc, pc};
        try {
        	callbackHandler.handle(callbacks);
        }
        catch (Exception e) {
        	throw new LoginException();
        } 
	    identity = new SimplePrincipal(nc.getName());
	    //logger.log(Level.INFO, "LOGIN  IDENTY = {0}",nc.getName());
	    char[] tmpPassword = pc.getPassword();
	    if( tmpPassword != null ){
	    	credential = new char[tmpPassword.length];
	    	System.arraycopy(tmpPassword, 0, credential, 0, tmpPassword.length);
	        pc.clearPassword();
	    }
	    
	}

	
	
	
	
	
	
	protected void getAcademic() throws LoginException {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("vipLogin");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		
		Root<User> root = cq.from(User.class);
		cq.where(  cb.equal(root.get(User_.email), identity.getName()));
		
		try{
			academic = em.createQuery(cq).getSingleResult();
		}
		
		catch (Exception e) {
			academic =null;
			e.printStackTrace();
			throw new LoginException();
		}
		
		tx.commit();
		em.close();
		emf.close();
		
	}
	
	
	


	
	
	protected Group getRoleSets() throws LoginException {
		SimpleGroup group = new SimpleGroup("Roles");
		if(academic!=null){
			Iterator<UserType> lista = academic.getUserTypes().iterator();
			while(lista.hasNext()){
				String role = lista.next().toString();
				logger.log(Level.INFO, "-----------------------------{0}-----------------------------------",role);
				group.addMember(new SimplePrincipal(role));
			}
		}
        return group ;
	}
	
	
	
	
	protected void validateUser( ) throws LoginException {
		try {
			String md5password = TextUtils.produceMd5Hash(new String(credential));
			if(academic!=null){
				if( md5password == null || !md5password.equals(academic.getPassword()) ){
		            throw new LoginException(); 
				}
				else{
					return;
				}
			}
		} catch (NoSuchAlgorithmException e) {
			throw new LoginException();
		}       
    }
	
	
}
