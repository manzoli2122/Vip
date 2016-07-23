package vip.core.domain;

import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;



@Entity
public class VipConfiguration extends PersistentObjectSupport {
	
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar creationDate;
	
	
	@Basic
	private String institutionAcronym;
	
	@NotNull
	@ManyToOne
	private User administrador;
	
	
	@NotNull
	private String smtpServerAddress;
	
	
	@NotNull
	private Integer smtpServerPort;
	
	
	@NotNull
	private String smtpUsername;
	
	
	@NotNull
	private String smtpPassword;
	
	

	
	
	
	
	/** Constructor. */
	public VipConfiguration() { }

	
	/** GETTERS AND SETTERS */
	
	public Calendar getCreationDate() {	return creationDate;}
	public void setCreationDate(Calendar creationDate) {this.creationDate = creationDate;}
	
	public String getInstitutionAcronym() {	return institutionAcronym;}
	public void setInstitutionAcronym(String institutionAcronym) { this.institutionAcronym = institutionAcronym;}	

	public String getSmtpServerAddress() { 	return smtpServerAddress; }
	public void setSmtpServerAddress(String smtpServerAddress) { this.smtpServerAddress = smtpServerAddress; }

	public Integer getSmtpServerPort() { return smtpServerPort; }
	public void setSmtpServerPort(Integer smtpServerPort) { this.smtpServerPort = smtpServerPort; }

	public String getSmtpUsername() { return smtpUsername; }
	public void setSmtpUsername(String smtpUsername) { 	this.smtpUsername = smtpUsername; }

	public String getSmtpPassword() { return smtpPassword; 	}
	public void setSmtpPassword(String smtpPassword) { 	this.smtpPassword = smtpPassword; 	}
	
	public User getAdministrador() { return administrador; }
	public void setAdministrador(User administrador) { this.administrador = administrador;}

	
}
