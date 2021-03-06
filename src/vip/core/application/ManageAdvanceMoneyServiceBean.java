package vip.core.application;

import java.util.Calendar;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import vip.core.domain.AdvanceMoney;
import vip.core.persistence.AdvanceMoneyDAO;
import vip.kernel.application.SessionInformation;;


@Stateless
@DeclareRoles({ "Admin" , "Employee" , "Cliente"})
@RolesAllowed({ "Admin" })
public class ManageAdvanceMoneyServiceBean extends CrudServiceBean<AdvanceMoney> implements ManageAdvanceMoneyService{

	
	private static final long serialVersionUID = 1L;

	@EJB
	private AdvanceMoneyDAO advanceMoneyDAO;
	
	@EJB 
	private SessionInformation sessionInformation;
		
	
	@Override
	public BaseDAO<AdvanceMoney> getDAO() {
		return advanceMoneyDAO;
	}

	
	
	@Override
	public void validateCreate(AdvanceMoney entity) throws CrudException {	
		
		entity.setCreateRegister(sessionInformation.getCurrentUser());
		entity.setLastUpdateRegister(sessionInformation.getCurrentUser());
		
		Calendar hoje = Calendar.getInstance();
		entity.setLastUpdateDate(hoje);
	
	}
	
	
	@Override
	public void validateUpdate(AdvanceMoney entity) throws CrudException {
		
		if( entity.getSalary() != null ) {
			int i = 9/0;
		}
		
		entity.setLastUpdateRegister(sessionInformation.getCurrentUser());
		entity.setLastUpdateDate(Calendar.getInstance());
		
		if(sessionInformation.isSuperUsuario()|| sessionInformation.isGerente()){
			return ;
		}
		
		if(!entity.getCreateRegister().equals(sessionInformation.getCurrentUser())  ){
			int i = 9/0;
		}
	}
	
	
	
	
	
	
	
	@Override
	public void validateDelete(AdvanceMoney entity) throws CrudException {
		
		if(entity.getSalary()!=null){
			int i = 9/0;
		}

		if(sessionInformation.isSuperUsuario()|| sessionInformation.isGerente()){
			return ;
		}
		
		if(!entity.getCreateRegister().equals(sessionInformation.getCurrentUser())  ){
			int i = 9/0;
		}
	}
	
	
	
	

}
