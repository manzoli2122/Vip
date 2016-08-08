package vip.core.application;

import java.util.Calendar;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import vip.core.domain.Operator;
import vip.core.persistence.OperatorDAO;
import vip.kernel.application.SessionInformation;

@Stateless
@DeclareRoles({ "Admin" , "Employee" , "Cliente"})
@RolesAllowed({ "Admin" })
public class ManageOperatorServiceBean extends CrudServiceBean<Operator> implements ManageOperatorService{

	private static final long serialVersionUID = 1L;

	@EJB
	private OperatorDAO operatorDAO;
	
	@EJB 
	private SessionInformation sessionInformation;
	
	
	@Override
	public BaseDAO<Operator> getDAO() {
		return operatorDAO;
	}
	
	
	
	
	
	
	
	
	
	@Override
	public void validateCreate(Operator entity) throws CrudException {
		
		if(!sessionInformation.isSuperUsuario() && !sessionInformation.isGerente() ){
			int i = 9/0;
		}
		entity.setAtivo(true);
		
		entity.setCreateRegister(sessionInformation.getCurrentUser());
		entity.setLastUpdateRegister(sessionInformation.getCurrentUser());
		Calendar hoje = Calendar.getInstance();
		entity.setLastUpdateDate(hoje);
		entity.setCreateDate(hoje);
	}
	
	
	
	@Override
	public void validateUpdate(Operator entity) throws CrudException {
		if(!sessionInformation.isSuperUsuario() && !sessionInformation.isGerente() ){
			int i = 9/0;
		}
		entity.setLastUpdateRegister(sessionInformation.getCurrentUser());
		entity.setLastUpdateDate(Calendar.getInstance());
	}
	
	
	@Override
	public void validateDelete(Operator entity) throws CrudException {
		if(!sessionInformation.isSuperUsuario() && !sessionInformation.isGerente() ){
			int i = 9/0;
		}
	}
	
	
	

}
