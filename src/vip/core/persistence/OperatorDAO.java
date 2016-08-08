package vip.core.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import vip.core.domain.Operator;

@Local
public interface OperatorDAO extends BaseDAO<Operator>{

	List<Operator> retrieveAtivos();

}
