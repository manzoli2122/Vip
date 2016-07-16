package vip.core.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import vip.core.domain.VipConfiguration;



@Local
public interface VipConfigurationDAO extends BaseDAO<VipConfiguration> {
	/**
	 * TODO: document this method.
	 * 
	 * @return
	 * @throws PersistentObjectNotFoundException
	 */
	VipConfiguration retrieveCurrentConfiguration() throws PersistentObjectNotFoundException;
}
