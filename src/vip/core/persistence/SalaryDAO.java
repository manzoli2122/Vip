package vip.core.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import vip.core.domain.Salary;

@Local
public interface SalaryDAO  extends BaseDAO<Salary>{

}
