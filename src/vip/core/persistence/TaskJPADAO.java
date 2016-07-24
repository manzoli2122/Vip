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
import vip.core.domain.Task;
import vip.core.domain.Task_;


@Stateless
public class TaskJPADAO extends BaseJPADAO<Task> implements TaskDAO {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName="Vip")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	protected List<Order> getOrderList(CriteriaBuilder cb, Root<Task> root) {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(cb.desc(root.get(Task_.ativo)));
		orderList.add(cb.asc(root.get(Task_.name)));
		return orderList;
	}
	
	
	@Override
	public List<Task> findByName(String param) {	
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Task> cq = cb.createQuery(Task.class);
		Root<Task> root = cq.from(Task.class);
		
		cq.where( cb.like(cb.lower(root.get(Task_.name)), param.toLowerCase()+"%"));
		
		List<Task> result = entityManager.createQuery(cq).getResultList();
		return result;
	}

}
