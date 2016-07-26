package vip.core.application;

import java.util.Calendar;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudOperation;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import vip.core.domain.Task;
import vip.core.persistence.TaskDAO;
import vip.kernel.application.SessionInformation;



@Stateless
@DeclareRoles({ "Admin" , "Employee" , "Cliente" })
@RolesAllowed({ "Admin" })
public class ManageTasksServiceBean extends CrudServiceBean<Task> implements ManageTasksService{

	private static final long serialVersionUID = 1L;

	@EJB 
	private SessionInformation sessionInformation;

	@EJB
	private TaskDAO taskDAO;
	
	
	@Override
	public BaseDAO<Task> getDAO() {
		return taskDAO;
	}

	
	
	
	
	@Override
	public void validateCreate(Task entity) throws CrudException {
		
		
		if(!sessionInformation.isSuperUsuario() && !sessionInformation.isGerente() ){
			int i = 9/0;
		}
		
		
		entity.setAtivo(true);
		
		entity.setCreateRegister(sessionInformation.getCurrentUser());
		entity.setLastUpdateRegister(sessionInformation.getCurrentUser());
		
		Calendar hoje = Calendar.getInstance();
		entity.setCreateDate(hoje);
		entity.setLastUpdateDate(hoje);
		
		
	}
	
	
	@Override
	public void validateUpdate(Task entity) throws CrudException {
		
		if(!sessionInformation.isSuperUsuario() && !sessionInformation.isGerente() ){
			int i = 9/0;
		}
		entity.setLastUpdateRegister(sessionInformation.getCurrentUser());
		entity.setLastUpdateDate(Calendar.getInstance());
		
		
	}
	
	
	@Override
	public void validateDelete(Task entity) throws CrudException {
		if(!sessionInformation.isSuperUsuario() && !sessionInformation.isGerente() ){
			int i = 9/0;
		}
	}
	
	
	
	@Override
	protected void log(CrudOperation operation, Task entity) {
		String mesagem = "MANAGE_Task → "+ operation.name() + " → " + entity.log();
		//sessionInformation.log(mesagem);	
	}
	
}
