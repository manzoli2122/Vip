package vip.core.application;

import java.text.SimpleDateFormat;
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
		
		entity.setAtivo(true);
		
		entity.setCreateRegister(sessionInformation.getCurrentUser());
		entity.setLastUpdateRegister(sessionInformation.getCurrentUser());
		
		Calendar hoje = Calendar.getInstance();
		entity.setCreateDate(hoje);
		entity.setLastUpdateDate(hoje);
		
		
	}
	
	
	@Override
	public void validateUpdate(Task entity) throws CrudException {
		
		entity.setLastUpdateRegister(sessionInformation.getCurrentUser());
		entity.setLastUpdateDate(Calendar.getInstance());
		
		
	}
	
	
	@Override
	public void validateDelete(Task entity) throws CrudException {
		
		if(!isDeletavel(entity)){
			int i = 9/0;
		}
		
	}
	
	
	
	@Override
	public boolean isDeletavel(Task entity){
		if(sessionInformation.isAdmin()){
			return true;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar hoje = Calendar.getInstance();
		String dataAtualFormatada = sdf.format(hoje.getTime());
		String dataEventoFormatada = sdf.format(entity.getCreateDate().getTime());
			
		if(dataEventoFormatada.equals(dataAtualFormatada)){
			return true;
		}
		return false;
	}
	
	
	
	@Override
	protected void log(CrudOperation operation, Task entity) {
		String mesagem = "MANAGE_Task → "+ operation.name() + " → " + entity.log();
		//sessionInformation.log(mesagem);	
	}
	
}
