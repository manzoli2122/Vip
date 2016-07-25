package vip.core.persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import vip.core.domain.VipConfiguration;
import vip.core.domain.VipConfiguration_;


@Stateless
public class VipConfigurationJPADAO extends BaseJPADAO<VipConfiguration> implements VipConfigurationDAO {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(VipConfigurationJPADAO.class.getCanonicalName());

	@PersistenceContext(unitName="Vip")
	private EntityManager entityManager;


	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public VipConfiguration retrieveCurrentConfiguration() throws PersistentObjectNotFoundException {
		logger.log(Level.FINE, "Retrieving current (latest) Vip configuration...");

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<VipConfiguration> cq = cb.createQuery(VipConfiguration.class);
		Root<VipConfiguration> root = cq.from(VipConfiguration.class);

		cq.orderBy(cb.desc(root.get(VipConfiguration_.createDate)));
		
		List<VipConfiguration> result = entityManager.createQuery(cq).getResultList();
		try {
			VipConfiguration cfg = result.get(0);
			return cfg;
		}
		catch (IndexOutOfBoundsException e) {
			throw new PersistentObjectNotFoundException(e, getDomainClass());
		}
	}
}
