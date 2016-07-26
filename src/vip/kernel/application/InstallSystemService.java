package vip.kernel.application;

import java.io.Serializable;

import javax.ejb.Local;

import vip.core.domain.User;
import vip.core.exceptions.SystemInstallFailedException;
import vip.kernel.domain.VipConfiguration;



@Local
public interface InstallSystemService extends Serializable {
	
	
	void installSystem(VipConfiguration config, User admin) throws SystemInstallFailedException;

}
