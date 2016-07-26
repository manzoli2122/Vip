package vip.secretariat.application;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import vip.core.domain.User;
import vip.core.domain.UserType;
import vip.core.persistence.UserDAO;
import vip.kernel.application.CoreInformation;





@Stateless
@DeclareRoles({ "Admin" , "Employee" , "Cliente" })
@RolesAllowed({ "Admin" , "Employee" })
public class ManageClientServiceBean extends CrudServiceBean<User> implements ManageClientService{

	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	
	/** The DAO for Academic objects. */
	@EJB
	private UserDAO academicDAO;
	
	
	/** The information singleton for the core module. */
	@EJB    	
	private CoreInformation coreInformation;
	
	
	
	
	@Override
	public BaseDAO<User> getDAO() {
		return academicDAO;
	}


	
	
	
	@Override
	public void validateCreate(User entity) throws CrudException {
		
		entity.setUserTypes(new ArrayList<UserType>());
		entity.getUserTypes().add(UserType.Cliente);
		
		Calendar now = Calendar.getInstance();
		entity.setLastUpdateDate(now);
		entity.setCreationDate(now);
		
		sendEmailRegister(entity);
		
		try {
			entity.setPassword(TextUtils.produceMd5Hash("salao123"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void validateUpdate(User entity) throws CrudException {
		entity.setLastUpdateDate(Calendar.getInstance());
		if(entity.getUserTypes().contains(UserType.Admin)||entity.getUserTypes().contains(UserType.Employee)){
			int i = 9/0;
		}
		
	}
	
	@Override
	public void validateDelete(User entity) throws CrudException {
		if(entity.getUserTypes().contains(UserType.Admin)||entity.getUserTypes().contains(UserType.Employee)){
			int i = 9/0;
		}
	}
	


	@Override
	public void sendEmailRegister(User entity) {
		// TODO Auto-generated method stub	
	}
	
	
	
	
	
}
