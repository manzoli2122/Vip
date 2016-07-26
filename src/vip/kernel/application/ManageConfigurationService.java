package vip.kernel.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import vip.kernel.domain.VipConfiguration;




@Local
public interface ManageConfigurationService extends CrudService<VipConfiguration> {

}
