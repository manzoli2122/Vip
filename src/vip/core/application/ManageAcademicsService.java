package vip.core.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import vip.core.domain.Academic;

@Local
public interface ManageAcademicsService extends CrudService<Academic>{

	void sendEmailRegister(Academic entity);

}
