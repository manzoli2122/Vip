package vip.core.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import vip.core.domain.AdvanceMoney;
import vip.core.domain.User;

@Local
public interface AdvanceMoneyDAO extends BaseDAO<AdvanceMoney>{

	List<AdvanceMoney> retrieveValesAbertos(User funcionario);

}
