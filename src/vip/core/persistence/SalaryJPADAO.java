package vip.core.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import vip.core.domain.Salary;
import vip.core.domain.Salary_;

@Stateless
public class SalaryJPADAO extends BaseJPADAO<Salary> implements  SalaryDAO {
	
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="Vip")
	private EntityManager entityManager;
	
	
	
	@Override
	public Class<Salary> getDomainClass() {
		return Salary.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	
	
	@Override
	protected List<Order> getOrderList(CriteriaBuilder cb, Root<Salary> root) {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(cb.desc(root.get(Salary_.createDate)));
		return orderList;
	}
	
}
