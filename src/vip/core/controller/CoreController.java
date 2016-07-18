package vip.core.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;
import vip.core.domain.User;
import vip.core.domain.UserType;
import vip.core.persistence.UserDAO;




@Named
@ApplicationScoped
public class CoreController implements Serializable{


	private static final long serialVersionUID = 1L;

	
	/** The DAO for user objects. */
	@EJB    	
	private UserDAO userDAO;
	
	
	
	
	/** JSF Converter for user objects. */
	private PersistentObjectConverterFromId<User> userConverter;

	
	
	/** Getter for userConverter */
	public Converter getUserConverter() {
		if (userConverter == null) {
			userConverter = new PersistentObjectConverterFromId<User>(userDAO);
		}
		return userConverter;
	}
	
	
	
	public List<User> getEmployees(){
		List<User> lista = userDAO.retrieveAll();
		List<User> teacher = new ArrayList<User>();
		for(User user : lista){
			Iterator<UserType> it = user.getUserTypes().iterator();
			while(it.hasNext()){
				if(it.next().equals(UserType.Employee)){
					teacher.add(user);
				}
			}
		}
		return teacher;
	}
	
	
	
	public String login(){
		return "/login/index.xhtml?faces-redirect=true" ;
	}
	
	
	
	public UserType[] getUserType() {
		return UserType.values();
	}



	
	
	
}
