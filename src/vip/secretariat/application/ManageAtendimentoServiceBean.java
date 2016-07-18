package vip.secretariat.application;

import java.util.Iterator;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudOperation;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import vip.secretariat.domain.Attendance;
import vip.secretariat.domain.EmployeeAttendance;
import vip.secretariat.persistence.AttendanceDAO;
import vip.core.application.SessionInformation;






@Stateless
@DeclareRoles({ "Admin" , "Employee" })
@RolesAllowed({ "Admin", "Employee" })
public class ManageAtendimentoServiceBean extends CrudServiceBean<Attendance> implements ManageAtendimentoService{

	private static final long serialVersionUID = 1L;

	@EJB 
	private SessionInformation sessionInformation;

	@EJB
	private AttendanceDAO atendimenoDAO;
	
	
	@Override
	public BaseDAO<Attendance> getDAO() {
		return atendimenoDAO;
	}

	
	
	@Override
	protected void log(CrudOperation operation, Attendance entity) {
		String mesagem = "MANAGE_ATENDIMENTO → "+ operation.name() + " → " + entity.toString();
		//sessionService.log(mesagem);
	}
	
	
	
	
	
	
	 @Override
	public void validateCreate(Attendance entity) throws CrudException {
		CrudException crudException = null;
		Double servico = entity.calcularValorServicos();
		Double pagamento = entity.calcularValorPagamentos();
		
		String crudExceptionMessage = " VALOR DO SERVICO ESTA DIFERENTE DO VALOR DO PAGAMENTO ";
			
		Double dif = pagamento - servico;
			
		if ((dif*dif)>0.0025) {
			crudException = addValidationError(crudException, crudExceptionMessage, "pagamentos", "manageAtendimento.error.valor");
			//crudException.addValidationError("password", "manageGameAccount.error.repeated", null);
		}
		if (crudException != null) throw crudException;
	}
	
	 @Override
	public void validateUpdate(Attendance entity) throws CrudException {
		CrudException crudException = null;
		Double servico = entity.calcularValorServicos();
		Double pagamento = entity.calcularValorPagamentos();
		
		String crudExceptionMessage = " VALOR DO SERVICO ESTA DIFERENTE DO VALOR DO PAGAMENTO ";
				
		Double dif = pagamento - servico;
				
		if ((dif*dif)>0.0025) {
			crudException = addValidationError(crudException, crudExceptionMessage, "pagamentos", "manageAtendimento.error.valor");
			//crudException.addValidationError("password", "manageGameAccount.error.repeated", null);
		}
		if (crudException != null) throw crudException;
	}
	
	@Override
	public void validateDelete(Attendance entity) throws CrudException {
		CrudException crudException = null;
		Iterator<EmployeeAttendance> iter = entity.getListServicos().iterator();
		String crudExceptionMessage = " ATENDIMENTO NÃO PODE SER APAGADO, JÁ CONSTA PAGAMENTO PARA ELE";	
		while(iter.hasNext()){
			if(iter.next().getSalario()!=null){
				crudException = addValidationError(crudException, crudExceptionMessage, "teste", "");
				break;
			}
		}
		if (crudException != null) throw crudException;
	}
	

}
