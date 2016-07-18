package vip.secretariat.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import vip.secretariat.domain.Payment;
import vip.secretariat.domain.Payment_;
import vip.secretariat.domain.FormOfPayment;

@Stateless
public class PagamentoJPADAO extends BaseJPADAO<Payment> implements PagamentoDAO{

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="Vip")
	private EntityManager entityManager;
	
	
	@Override
	public Class<Payment> getDomainClass() {
		return Payment.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	protected List<Order> getOrderList(CriteriaBuilder cb, Root<Payment> root) {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(cb.asc(root.get(Payment_.createDate)));
		return orderList;
	}
	
	
	
	@Override
	public List<Payment> retrieveCartao(Date init , Date fim , int parcela){
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Payment> cq = cb.createQuery(getDomainClass());
		Root<Payment> root = cq.from(getDomainClass());
		
		cq.where(
				cb.between(root.get(Payment_.createDate), init , fim),
				cb.equal(root.get(Payment_.formOfPayment), FormOfPayment.Cartao_Credito),
				cb.gt(root.get(Payment_.parcelas), parcela)
		);
		
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
		
		cq.where(
				cb.equal(root.get(Payment_.createDate), dia ),
				cb.equal(root.get(Payment_.formOfPayment), FormOfPayment.Cartao_Credito)
		);
		
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
		
		cq.where(
				cb.equal(root.get(Payment_.createDate), dia ),
				cb.equal(root.get(Payment_.formOfPayment), FormOfPayment.Dinheiro)
		);
		
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
		
		cq.where(
				cb.equal(root.get(Payment_.createDate), dia ),
				cb.equal(root.get(Payment_.formOfPayment), FormOfPayment.Cartao_Debito)
		);
		
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
		
		cq.where(
				cb.equal(root.get(Payment_.createDate), dia ),
				cb.equal(root.get(Payment_.formOfPayment), FormOfPayment.Cheque)
		);
		
		applyOrdering(cb, root, cq);
		
		List<Payment> result = em.createQuery(cq).getResultList();
		return result;
	}

	
	
}
