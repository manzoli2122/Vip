package vip.secretariat.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import vip.secretariat.domain.Attendance;

@Local
public interface AttendanceDAO  extends BaseDAO<Attendance> {

}
