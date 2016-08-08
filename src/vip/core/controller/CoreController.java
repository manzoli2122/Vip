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
import vip.core.domain.Operator;
import vip.core.domain.Task;
import vip.core.domain.User;
import vip.core.domain.UserType;
import vip.core.persistence.OperatorDAO;
import vip.core.persistence.TaskDAO;
import vip.core.persistence.UserDAO;
import vip.kernel.domain.Gender;




@Named
@ApplicationScoped
public class CoreController implements Serializable{


	private static final long serialVersionUID = 1L;

	
	/** The DAO for user objects. */
	@EJB    	
	private UserDAO userDAO;
	
	@EJB    	
	private TaskDAO taskDAO;
	
	@EJB    	
	private OperatorDAO operatorDAO;
	
	
	public Gender[] getGender() {
		return Gender.values();
	}
	
	
	/** JSF Converter for user objects. */
	private PersistentObjectConverterFromId<User> userConverter;

	/** Getter for userConverter */
	public Converter getUserConverter() {
		if (userConverter == null) {
			userConverter = new PersistentObjectConverterFromId<User>(userDAO);
		}
		return userConverter;
	}
	
	
	
	
	/** JSF Converter for user objects. */
	private PersistentObjectConverterFromId<Operator> operatorConverter;

	/** Getter for userConverter */
	public Converter getOperatorConverter() {
		if (operatorConverter == null) {
			operatorConverter = new PersistentObjectConverterFromId<Operator>(operatorDAO);
		}
		return operatorConverter;
	}
	
	
	
	
	
	/** JSF Converter for user objects. */
	private PersistentObjectConverterFromId<Task> taskConverter;

	/** Getter for userConverter */
	public Converter getTaskConverter() {
		if (taskConverter == null) {
			taskConverter = new PersistentObjectConverterFromId<Task>(taskDAO);
		}
		return taskConverter;
	}
	
	
	public List<Task> getTasksAtivos(){
		List<Task> lista = taskDAO.retrieveAtivos();
		return lista;
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
	
	
	public List<User> getAdministradores(){
		List<User> lista = userDAO.retrieveAll();
		List<User> teacher = new ArrayList<User>();
		for(User user : lista){
			Iterator<UserType> it = user.getUserTypes().iterator();
			while(it.hasNext()){
				if(it.next().equals(UserType.Admin)){
					teacher.add(user);
				}
			}
		}
		return teacher;
	}
	
	
	public List<Operator> getOperatorAtivos(){
		List<Operator> lista = operatorDAO.retrieveAtivos();
		return lista;
	}
	
	
	
	
	
	
	
	public List<User> getClientes(){
		List<User> lista = userDAO.retrieveAll();
		return lista;
	}
	
	
	
	public String login(){
		return "/login/index.xhtml?faces-redirect=true" ;
	}
	
	
	
	public UserType[] getUserType() {
		return UserType.values();
	}



	
	
	
}
