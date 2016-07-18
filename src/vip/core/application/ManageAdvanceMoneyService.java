package vip.core.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import vip.core.domain.AdvanceMoney;


@Local
public interface ManageAdvanceMoneyService extends CrudService<AdvanceMoney>{

}
