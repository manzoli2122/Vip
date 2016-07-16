package vip.core.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Entity
public class VipConfiguration extends PersistentObjectSupport {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The timestamp of the moment this configuration came in effect. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	/** Acronym of the institution that is using Marvin. */
	@Basic
	private String institutionAcronym;
	
	/** Constructor. */
	public VipConfiguration() { }

	/** Getter for creationDate. */
	public Date getCreationDate() {
		return creationDate;
	}

	/** Getter for institutionAcronym. */
	public String getInstitutionAcronym() {
		return institutionAcronym;
	}

	/** Setter for creationDate. */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/** Setter for institutionAcronym. */
	public void setInstitutionAcronym(String institutionAcronym) {
		this.institutionAcronym = institutionAcronym;
	}
	
	
	
	
	
	/** Address for the SMTP server that sends e-mail. */
	@NotNull
	private String smtpServerAddress;
	
	/** Port for the SMTP server that sends e-mail. */
	@NotNull
	private Integer smtpServerPort;
	
	/** Username to connect to the SMTP server that sends email. */
	@NotNull
	private String smtpUsername;
	
	/** Password to connect to the SMTP server that sends email. */
	@NotNull
	private String smtpPassword;
	
	
	
	/** The date the moment you sent e-mail to update data. */
	@Temporal(TemporalType.DATE)
	private Date updateDataDate;
	

	
	/** GETTERS AND SETTERS */
	public String getSmtpServerAddress() { 	return smtpServerAddress; }
	public void setSmtpServerAddress(String smtpServerAddress) { this.smtpServerAddress = smtpServerAddress; }

	public Integer getSmtpServerPort() { return smtpServerPort; }
	public void setSmtpServerPort(Integer smtpServerPort) { this.smtpServerPort = smtpServerPort; }

	public String getSmtpUsername() { return smtpUsername; }
	public void setSmtpUsername(String smtpUsername) { 	this.smtpUsername = smtpUsername; }

	public String getSmtpPassword() { return smtpPassword; 	}
	public void setSmtpPassword(String smtpPassword) { 	this.smtpPassword = smtpPassword; 	}
	
	public Date getUpdateDataDate() { return updateDataDate; 	}
	public void setUpdateDataDate(Date updateDataDate) { this.updateDataDate = updateDataDate;}

	
}
