package vip.secretariat.persistence;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import vip.secretariat.domain.Payment;



@Stateless
public class PaymentJPADAO extends BaseJPADAO<Payment> implements PaymentDAO{

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="Vip")
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	

	@Override
	public List<Payment> retrieveCartao(Date init , Date fim , int parcela){
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Payment> cq = cb.createQuery(getDomainClass());
		Root<Payment> root = cq.from(getDomainClass());
		applyOrdering(cb, root, cq);
		
		List<Payment> result = em.createQuery(cq).getResultList();
		return result;
	}
	
	
	@Override
	public List<Payment> retrieveCartaoDiario(Date dia){
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Payment> cq = cb.createQuery(getDomainClass());
		Root<Payment> root = cq.from(getDomainClass());
		
		applyOrdering(cb, root, cq);
		
		List<Payment> result = em.createQuery(cq).getResultList();
		return result;
	}

	
	@Override
	public List<Payment> retrieveDinheiroDiario(Date dia){
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Payment> cq = cb.createQuery(getDomainClass());
		Root<Payment> root = cq.from(getDomainClass());
		
		applyOrdering(cb, root, cq);
		
		List<Payment> result = em.createQuery(cq).getResultList();
		return result;
	}
	
	
	@Override
	public List<Payment> retrieveDebitoDiario(Date dia){
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Payment> cq = cb.createQuery(getDomainClass());
		Root<Payment> root = cq.from(getDomainClass());
		
		applyOrdering(cb, root, cq);
		
		List<Payment> result = em.createQuery(cq).getResultList();
		return result;
	}
	
	
	
	@Override
	public List<Payment> retrieveChequeDiario(Date dia){
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Payment> cq = cb.createQuery(getDomainClass());
		Root<Payment> root = cq.from(getDomainClass());
				
		applyOrdering(cb, root, cq);
		
		List<Payment> result = em.createQuery(cq).getResultList();
		return result;
	}

	
	
}
