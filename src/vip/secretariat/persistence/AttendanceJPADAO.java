package vip.secretariat.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import vip.secretariat.domain.Attendance;
import vip.secretariat.domain.Attendance_;


@Stateless
public class AttendanceJPADAO extends BaseJPADAO<Attendance> implements AttendanceDAO {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName="Vip")
	private EntityManager entityManager;
	

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	protected List<Order> getOrderList(CriteriaBuilder cb, Root<Attendance> root) {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(cb.desc(root.get(Attendance_.createDate)));
		return orderList;
	}}
