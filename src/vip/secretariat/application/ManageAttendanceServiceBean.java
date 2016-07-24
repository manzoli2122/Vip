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
@DeclareRoles({ "Admin" , "Employee" , "Cliente" })
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
				 
		Double servico = entity.getValor();
		Double pagamento = entity.getValorPayments();
		
		Double dif =  pagamento - servico;
		
			
		if ((dif*dif)>0.0025) {
			int i = 9/0;
		}
		
		entity.setCreateRegister(sessionInformation.getCurrentUser());
		entity.setLastUpdateRegister(sessionInformation.getCurrentUser());
			
		Calendar hoje = Calendar.getInstance();
		//entity.setCreateDate(hoje);
		entity.setLastUpdateDate(hoje);
	}
	 
	 
	 
	 
	 
	//@RolesAllowed({ "Admin" })
	@Override
	public void validateUpdate(Attendance entity) throws CrudException {
		
		Double servico = entity.getValor();
		Double pagamento = entity.getValorPayments();
			
		Double dif =  pagamento - servico;
			
		if (((dif*dif)>0.0025)||!isAlteravel(entity)) {
			int i = 9/0;
		}
		
		entity.setLastUpdateRegister(sessionInformation.getCurrentUser());
		entity.setLastUpdateDate(Calendar.getInstance());
		
	}
	
	 
	 
	 
	 
	 
	//@RolesAllowed({ "Admin" })
	@Override
	public void validateDelete(Attendance entity) throws CrudException {	
				
		if( !isAlteravel(entity) ){
			int i = 9/0;
		}
		
	}
	

	
	
	
	
	@Override
	public boolean isAlteravel(Attendance attendance){
		
		Iterator<EmployeeAttendance> iter = attendance.getEmployeeAttendances().iterator();
		while(iter.hasNext()){
			if(iter.next().getSalary()!=null){
				return false;
			}
		}
		
		if(sessionInformation.isAdmin()){
			return true;
		}
		if(!sessionInformation.getCurrentUser().equals(attendance.getCreateRegister())){
			return false;
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
