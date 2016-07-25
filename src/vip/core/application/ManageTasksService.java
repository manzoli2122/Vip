package vip.core.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import vip.core.domain.Task;


@Local
public interface ManageTasksService extends CrudService<Task>{

	

}
