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
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import vip.core.domain.Operator;
import vip.core.domain.Operator_;
import vip.core.domain.Task;
import vip.core.domain.Task_;
import vip.kernel.domain.VipConfiguration;
import vip.kernel.domain.VipConfiguration_;

@Stateless
public class OperatorJPADAO extends BaseJPADAO<Operator> implements  OperatorDAO{

private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName="Vip")
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	@Override
	protected List<Order> getOrderList(CriteriaBuilder cb, Root<Operator> root) {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(cb.desc(root.get(Operator_.ativo)));
		orderList.add(cb.desc(root.get(Operator_.createDate)));
		return orderList;
	}
	
	
	
	@Override
	public List<Operator> retrieveAtivos() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Operator> cq = cb.createQuery(getDomainClass());
		Root<Operator> root = cq.from(getDomainClass());
		
		cq.where(cb.equal(root.get(Operator_.ativo), true));
		
		cq.select(root);

		applyOrdering(cb, root, cq);

		List<Operator> result = entityManager.createQuery(cq).getResultList();
		return result;
	}

	
	

}
