package vip.core.application;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import vip.secretariat.domain.Attendance;

@Local
public interface RelatorioDiarioService extends Serializable {

	
	List<Attendance> buscarByDate(Calendar inicio, Calendar fim);

	void salvarRelatorioDiario(List<Attendance> lista);

}
