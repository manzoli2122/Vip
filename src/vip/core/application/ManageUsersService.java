package vip.core.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import vip.core.domain.User;

@Local
public interface ManageUsersService extends CrudService<User>{

	void sendEmailRegister(User entity);

}
