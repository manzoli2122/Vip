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
import vip.core.domain.AdvanceMoney;
import vip.core.domain.AdvanceMoney_;
import vip.core.domain.User;


@Stateless
public class AdvanceMoneyJPADAO extends BaseJPADAO<AdvanceMoney> implements  AdvanceMoneyDAO {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName="Vip")
	private EntityManager entityManager;
	
	
	
	@Override
	public Class<AdvanceMoney> getDomainClass() {
		return AdvanceMoney.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	protected List<Order> getOrderList(CriteriaBuilder cb, Root<AdvanceMoney> root) {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(cb.desc(root.get(AdvanceMoney_.createDate)));
		return orderList;
	}

	
	
	
	@Override
	public List<AdvanceMoney> retrieveSalario(User funcionario) {
		if(funcionario==null) return null;
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<AdvanceMoney> cq = cb.createQuery(getDomainClass());
		Root<AdvanceMoney> root = cq.from(getDomainClass());
		
		cq.where(cb.equal(root.get(AdvanceMoney_.funcionario), funcionario),
				 cb.isNull(root.get(AdvanceMoney_.salary)));

		List<AdvanceMoney> result = em.createQuery(cq).getResultList();
		return result;
	}

}
