package vip.secretariat.persistence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import vip.kernel.application.CoreInformation;
import vip.secretariat.domain.Attendance;
import vip.secretariat.domain.Attendance_;


@Stateless
public class AttendanceJPADAO extends BaseJPADAO<Attendance> implements AttendanceDAO {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(AttendanceJPADAO.class.getCanonicalName());

	
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
	}
	
	
	@Override
	public List<Attendance> retrieveByDate(Calendar inicio , Calendar fim){
		
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Attendance> cq = cb.createQuery(getDomainClass());
		Root<Attendance> root = cq.from(getDomainClass());
		
		
		Calendar start = Calendar.getInstance();
		start.setTime(inicio.getTime());
				
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		
		
		Calendar end = Calendar.getInstance();
		end.setTime(fim.getTime());
		
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		
		
		logger.log(Level.INFO, "***********   {0} ***********   {1}  ********* ", new Object[] {start.getTime(), end.getTime() });
		
		
		cq.where(cb.between(root.get(Attendance_.createDate), start, end));
		
		
		List<Attendance> result = em.createQuery(cq).getResultList();
		return result;
	}




}
