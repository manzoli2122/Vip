package vip.secretariat.persistence;

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
import vip.core.domain.User;
import vip.secretariat.domain.EmployeeAttendance;
import vip.secretariat.domain.EmployeeAttendance_;

@Stateless
public class EmployeeAttendanceJPADAO extends BaseJPADAO<EmployeeAttendance> implements EmployeeAttendanceDAO{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName="Vip")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	@Override
	protected List<Order> getOrderList(CriteriaBuilder cb, Root<EmployeeAttendance> root) {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(cb.desc(root.get(EmployeeAttendance_.attendance)));
		return orderList;
	}
	
	
	@Override
	public List<EmployeeAttendance> retrieveServicoAberto(User funcionario){
		
		if(funcionario==null) return null;
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EmployeeAttendance> cq = cb.createQuery(getDomainClass());
		Root<EmployeeAttendance> root = cq.from(getDomainClass());
		
		cq.where(cb.equal(root.get(EmployeeAttendance_.employee), funcionario),
				 cb.isNull(root.get(EmployeeAttendance_.salary)));

		List<EmployeeAttendance> result = em.createQuery(cq).getResultList();
		return result;
	}
	
	
	
	
	

	
	
	
	
	

}
