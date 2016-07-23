package vip.secretariat.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import vip.core.domain.User;

@Local
public interface ManageClientService extends CrudService<User> {

	
	void sendEmailRegister(User entity);
	
	
}
