package vip.core.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import vip.core.domain.User;
import vip.core.domain.User_;


@Stateless
public class UserJPADAO extends BaseJPADAO<User> implements UserDAO {
	
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="Vip")
	private EntityManager entityManager;


	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	@Override
	protected List<Order> getOrderList(CriteriaBuilder cb, Root<User> root) {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(cb.asc(root.get(User_.name)));
		return orderList;
	}
	
	
	
	
	@Override
	public User retrieveByEmail(String email) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);

		cq.where(cb.equal(root.get(User_.email), email));
		User result = executeSingleResultQuery(cq, email);
		return result;
	}
}