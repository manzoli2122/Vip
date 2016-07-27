package vip.kernel.application;

import java.util.Calendar;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import vip.kernel.domain.VipConfiguration;
import vip.kernel.persistence.VipConfigurationDAO;





@Stateless
@DeclareRoles({ "Admin" , "Employee" , "Cliente" })
@RolesAllowed({ "Admin" })
public class ManageConfigurationServiceBean extends CrudServiceBean<VipConfiguration> implements ManageConfigurationService{

	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private VipConfigurationDAO vipConfigurationDAO;

	@EJB 
	private SessionInformation sessionInformation;
	
	
	@Override
	public BaseDAO<VipConfiguration> getDAO() {
		return vipConfigurationDAO;
	}
	
	@Override
	public void validateCreate(VipConfiguration entity) throws CrudException {
		
		if(!sessionInformation.isSuperUsuario()){
			int i = 9/0;
		}
		entity.setCreateRegister(sessionInformation.getCurrentUser());
		entity.setLastUpdateRegister(sessionInformation.getCurrentUser());
		
		Calendar hoje = Calendar.getInstance();
		entity.setLastUpdateDate(hoje);
		entity.setCreateDate(hoje);
	}
	
	
	@Override
	public void validateUpdate(VipConfiguration entity) throws CrudException {
		
		if(!sessionInformation.isSuperUsuario()){
			int i = 9/0;
		}
			
		entity.setLastUpdateRegister(sessionInformation.getCurrentUser());
		entity.setLastUpdateDate(Calendar.getInstance());
	
	}
	
	
	@Override
	public void validateDelete(VipConfiguration entity) throws CrudException {
		
		if(!sessionInformation.isSuperUsuario()){
			int i = 9/0;
		}
		VipConfiguration config = null;
		try {
			config = vipConfigurationDAO.retrieveCurrentConfiguration();
		} catch (PersistentObjectNotFoundException e) {	}		
			
		if(config.getId().equals(entity.getId())){
			int i = 9/0;
		}	
	}
	
	
	
	
}
