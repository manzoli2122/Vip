package vip.core.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class VipConfiguration extends PersistentObjectRegister implements Comparable<VipConfiguration>{

	
	private static final long serialVersionUID = 1L;
	
	@Basic
	private String institutionAcronym;
	
	@NotNull
	@ManyToOne
	private User administrador;
	
	
	@ManyToOne
	private User gerente;
	
	
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
	
	@Override
	public int compareTo(VipConfiguration o) {
		return super.compareTo(o);
	}

	
	/** GETTERS AND SETTERS */
	
	public User getGerente() {return gerente;}
	public void setGerente(User gerente) {this.gerente = gerente;}
	
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
