package vip.kernel.application;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.ufes.inf.nemo.util.TextUtils;
import vip.core.domain.User;
import vip.core.domain.UserType;
import vip.core.exceptions.SystemInstallFailedException;
import vip.core.persistence.UserDAO;
import vip.core.persistence.VipConfigurationDAO;
import vip.kernel.domain.VipConfiguration;




@Stateless
public class InstallSystemServiceBean implements InstallSystemService {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(InstallSystemServiceBean.class.getCanonicalName());

	@EJB
	private UserDAO userDAO;
	
	@EJB
	private VipConfigurationDAO marvinConfigurationDAO;

	@EJB
	private CoreInformation coreInformation;

	
	
	@Override
	public void installSystem(VipConfiguration config, User admin) throws SystemInstallFailedException {
		logger.log(Level.FINER, "Installing system...");

		try {
			// Encodes the admin's password.
			admin.setPassword(TextUtils.produceMd5Hash(admin.getPassword()));
			
			// Register the last update date / creation date.
			Calendar now = Calendar.getInstance();
			admin.setLastUpdateDate(now);
			admin.setCreationDate(now);
			
			config.setLastUpdateDate(now);
			config.setCreateDate(now);
			
			admin.setUserTypes(new ArrayList<UserType>());
			admin.getUserTypes().add(UserType.Admin);
			admin.getUserTypes().add(UserType.Employee);
			
		
			
			logger.log(Level.FINE, "Admin's last update date have been set as: {0}", new Object[] { now });
			
			// Saves the administrator.
			logger.log(Level.FINER, "Persisting admin data...\n\t- Short name = {0}\n\t- Last update date = {1}", new Object[] { admin.getShortName(), admin.getLastUpdateDate() });
			userDAO.save(admin);
			logger.log(Level.FINE, "The administrator has been saved: {0} ({1})", new Object[] { admin.getName(), admin.getEmail() });
			
			admin = userDAO.refresh(admin);
			
			config.setAdministrador(admin);
			config.setCreateRegister(admin);
			config.setLastUpdateRegister(admin);
			
			
			// Saves Marvin's configuration.
			logger.log(Level.FINER, "Persisting configuration data...\n\t- Date = {0}\n\t- Acronym = {1}", new Object[] { config.getCreateDate(), config.getInstitutionAcronym() });
			marvinConfigurationDAO.save(config);
			logger.log(Level.FINE, "The configuration has been saved");
			
			// Reloads the bean that holds the configuration and determines if the system is installed.
			logger.log(Level.FINER, "Reloading core information...");
			coreInformation.init();
		}		
		catch (NoSuchAlgorithmException e) {
			// Logs and rethrows the exception for the controller to display the error to the user.
			logger.log(Level.SEVERE, "Could not find MD5 algorithm for password encription!", e);
			throw new SystemInstallFailedException(e);
		}
		catch (Exception e) {
			// Logs and rethrows the exception for the controller to display the error to the user.
			logger.log(Level.SEVERE, "Exception during system installation!", e);
			throw new SystemInstallFailedException(e);
		}
	}

}
