package vip.kernel.application;

import java.io.Serializable;
import javax.ejb.Local;
import vip.core.domain.User;




@Local
public interface SessionInformation extends Serializable {
	
	
	User getCurrentUser();
	
	
	boolean isAdmin();


	boolean isEmployee();


	boolean isSuperUsuario();


	boolean isGerente();
	
}
