package vip.secretariat.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import vip.secretariat.domain.Attendance;


@Local
public interface ManageAttendanceService extends CrudService<Attendance>{

	boolean isAlteravel(Attendance attendance);

}
