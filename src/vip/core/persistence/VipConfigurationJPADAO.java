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
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(VipConfigurationJPADAO.class.getCanonicalName());

	/** The application's persistent context provided by the application server. */
	@PersistenceContext(unitName="Vip")
	private EntityManager entityManager;


	/** @see br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/** @see br.VipConfigurationDAO.feees.sigme.core.persistence.VipConfigurationDAO#retrieveCurrentConfiguration() */
	@Override
	public VipConfiguration retrieveCurrentConfiguration() throws PersistentObjectNotFoundException {
		logger.log(Level.FINE, "Retrieving current (latest) Vip configuration...");

		// Constructs the query over the VipConfiguration class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<VipConfiguration> cq = cb.createQuery(VipConfiguration.class);
		Root<VipConfiguration> root = cq.from(VipConfiguration.class);

		// Orders the query descending by date.
		cq.orderBy(cb.desc(root.get(VipConfiguration_.creationDate)));
		
		// Retrieves and returns the latest configuration (first entity returned). If the query returns an empty list, throws an exception.
		List<VipConfiguration> result = entityManager.createQuery(cq).getResultList();
		try {
			VipConfiguration cfg = result.get(0);
			logger.log(Level.INFO, "Retrieve current configuration returned a VipConfiguration with institution \"{0}\" and creation date \"{1}\"", new Object[] { cfg.getInstitutionAcronym(), cfg.getCreationDate() });
			return cfg;
		}
		catch (IndexOutOfBoundsException e) {
			throw new PersistentObjectNotFoundException(e, getDomainClass());
		}
	}
}
