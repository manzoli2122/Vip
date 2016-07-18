package vip.core.application;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudOperation;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import vip.core.domain.AdvanceMoney;
import vip.core.persistence.AdvanceMoneyDAO;;


@Stateless
@DeclareRoles({ "Admin" , "Employee" })
@RolesAllowed({ "Admin" })
public class ManageAdvanceMoneyServiceBean extends CrudServiceBean<AdvanceMoney> implements ManageAdvanceMoneyService{

	
	private static final long serialVersionUID = 1L;

	@EJB
	private AdvanceMoneyDAO valeDAO;
	
	@EJB 
	private SessionInformation sessionInformation;
	
	
	
	
	@Override
	public BaseDAO<AdvanceMoney> getDAO() {
		return valeDAO;
	}

	@Override
	public void validateCreate(AdvanceMoney entity) throws CrudException {
				
		entity.setRegister(sessionInformation.getCurrentUser());
		
		super.validateCreate(entity);
	}
	
	
	@Override
	public void validateUpdate(AdvanceMoney entity) throws CrudException {

		if(!entity.getRegister().equals(sessionInformation.getCurrentUser())){
			CrudException crudException = null;
			String crudExceptionMessage =  "Este vale não pode ser alterado.";
			crudException = addValidationError(crudException, crudExceptionMessage, null , "manageAdvanceMoney.error.update");
			throw crudException;
		}
		
		super.validateUpdate(entity);
	}
	
	
	
	@Override
	public void validateDelete(AdvanceMoney entity) throws CrudException {
		
		if(entity.getSalary()==null){
			int i = 9/0;
		}
		super.validateDelete(entity);
	}
	
	
	
	

}
