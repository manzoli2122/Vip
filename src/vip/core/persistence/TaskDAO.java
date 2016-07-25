package vip.core.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import vip.core.domain.Task;


@Local
public interface TaskDAO extends BaseDAO<Task>{

	List<Task> findByName(String param);

	List<Task> retrieveAtivos();

}
