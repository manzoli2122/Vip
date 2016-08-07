package vip.core.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import vip.core.domain.Salary;
import vip.secretariat.domain.Attendance;


@Local
public interface RelatorioPdfService extends Serializable{

	

	String RelatorioSalario(Salary salary);

	String RelatorioDiario(List<Attendance> lista);

}
