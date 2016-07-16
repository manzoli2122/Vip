package vip.core.application;

import java.io.Serializable;

import javax.ejb.Local;

import vip.core.domain.Academic;
import vip.core.domain.VipConfiguration;
import vip.core.exceptions.SystemInstallFailedException;



@Local
public interface InstallSystemService extends Serializable {
	/**
	 * TODO: document this method.
	 * @param config
	 * @param admin
	 * @throws SystemInstallFailedException
	 */
	void installSystem(VipConfiguration config, Academic admin) throws SystemInstallFailedException;
}
