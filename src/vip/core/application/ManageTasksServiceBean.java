package vip.core.application;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudOperation;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import vip.core.domain.Task;
import vip.core.persistence.TaskDAO;



@Stateless
@DeclareRoles({ "Admin" , "Employee" })
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
	protected void log(CrudOperation operation, Task entity) {
		String mesagem = "MANAGE_Task → "+ operation.name() + " → " + entity.log();
		//sessionInformation.log(mesagem);	
	}
	
}
