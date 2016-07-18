package vip.core.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import vip.core.domain.Salary;

@Local
public interface ManageSalaryService extends CrudService<Salary>{

	
	void sendEmailInfo(Salary salario);

}
