package vip.secretariat.application;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import vip.secretariat.domain.Attendance;
import vip.secretariat.domain.EmployeeAttendance;
import vip.secretariat.persistence.AttendanceDAO;
import vip.core.application.SessionInformation;




@Stateless
@DeclareRoles({ "Admin" , "Employee" })
@RolesAllowed({ "Admin", "Employee" })
public class ManageAttendanceServiceBean extends CrudServiceBean<Attendance> implements ManageAttendanceService{

	private static final long serialVersionUID = 1L;

	@EJB 
	private SessionInformation sessionInformation;

	@EJB
	private AttendanceDAO attendanceDAO;
	
	
	@Override
	public BaseDAO<Attendance> getDAO() {
		return attendanceDAO;
	}

	
	
	
	
	
	
	
	
	 @Override
	public void validateCreate(Attendance entity) throws CrudException {
		
		 entity.setRegister(sessionInformation.getCurrentUser());
		 
		Double servico = entity.getValor();
		Double pagamento = entity.getValorPayments();
		
		Double dif =  pagamento - servico;
		
			
		if ((dif*dif)>0.0025) {
			int i = 9/0;
		}
		
	}
	 
	 
	 
	 
	 
	//@RolesAllowed({ "Admin" })
	@Override
	public void validateUpdate(Attendance entity) throws CrudException {
		
		Double servico = entity.getValor();
		Double pagamento = entity.getValorPayments();
			
		Double dif =  pagamento - servico;
			
		if ((dif*dif)>0.0025) {
			int i = 9/0;
		}
		
		if(!isAlteravel(entity)){
			int i = 9/0;
		}
	}
	
	 
	 
	 
	 
	 
	//@RolesAllowed({ "Admin" })
	@Override
	public void validateDelete(Attendance entity) throws CrudException {	
		Iterator<EmployeeAttendance> iter = entity.getEmployeeAttendances().iterator();
		
		if(!sessionInformation.getCurrentUser().equals(entity.getRegister()) && !sessionInformation.isAdmin()){
			int i = 9/0;
		}
		
		while(iter.hasNext()){
			if(iter.next().getSalary()!=null){
				int i = 9/0;
			}
		}
		
	}
	

	
	
	
	
	@Override
	public boolean isAlteravel(Attendance attendance){
		
		if(sessionInformation.isAdmin()){
			return true;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar hoje = Calendar.getInstance();
		String dataAtualFormatada = sdf.format(hoje.getTime());
		String dataEventoFormatada = sdf.format(attendance.getCreateDate().getTime());
			
		if(dataEventoFormatada.equals(dataAtualFormatada)){
			return true;
		}
		
		return false;
	}
	
	
	

}
