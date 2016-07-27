package vip.kernel.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import vip.kernel.domain.VipConfiguration;



@Local
public interface VipConfigurationDAO extends BaseDAO<VipConfiguration> {
	
	VipConfiguration retrieveCurrentConfiguration() throws PersistentObjectNotFoundException;
}
