package vip.kernel.application;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.inject.Named;

import vip.core.persistence.VipConfigurationDAO;
import vip.kernel.domain.VipConfiguration;
import br.ufes.inf.nemo.jbutler.ResourceUtil;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;



@Singleton
@Named("coreInfo")
public class CoreInformation implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(CoreInformation.class.getCanonicalName());

	private static final String QUOTES_FILE_PATH = "/META-INF/quotes.txt";
	
	private static final String DEFAULT_DECORATOR_NAME = "default";
	
	@EJB
	private VipConfigurationDAO vipConfigurationDAO;

	private VipConfiguration currentConfig;

	private Boolean systemInstalled;

	private String decorator = DEFAULT_DECORATOR_NAME;
	
	//private List<String> quotes;
	
	private Random random = new Random(System.currentTimeMillis());
	
	
	/** Initializer method. */
	@PostConstruct
	public void init() {
		
		try {
			currentConfig = vipConfigurationDAO.retrieveCurrentConfiguration();
			systemInstalled = true;
		}
		catch (PersistentObjectNotFoundException e) {
			systemInstalled = false;
		}
		
		/*
		quotes = new ArrayList<>();
		File quotesFile = ResourceUtil.getResourceAsFile(QUOTES_FILE_PATH);
		try (Scanner scanner = new Scanner(quotesFile)) {
			while (scanner.hasNextLine()) quotes.add(scanner.nextLine().trim());
		}
		catch (IOException e) {
			logger.log(Level.WARNING, "Could not load quotes from path: {0}", QUOTES_FILE_PATH);
			quotes.add("I didn't ask to be made.");
		}
		*/
	}

	/** Getter for currentConfig. */
	public VipConfiguration getCurrentConfig() {return currentConfig;}

	/** Getter for systemInstalled. */
	public Boolean getSystemInstalled() {return systemInstalled;}

	/** Getter for decorator. */
	public String getDecorator() {return decorator;}
	
	/** Gets a random quote from Marvin. 
	public String getQuote() {
		int idx = random.nextInt(quotes.size());
		return quotes.get(idx);
	}
	*/

}
