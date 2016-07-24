package vip.secretariat.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import vip.core.domain.User;
import vip.secretariat.domain.EmployeeAttendance;


@Local
public interface EmployeeAttendanceDAO extends BaseDAO<EmployeeAttendance>{

	

	List<EmployeeAttendance> retrieveServicoAberto(User funcionario);

}
