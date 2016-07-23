package vip.core.domain;

import java.util.Calendar;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;




@MappedSuperclass
public class PersistentObjectRegister extends PersistentObjectSupport {

	
	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne
	private User createRegister;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar createDate;
	
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastUpdateDate;
	
	
	@NotNull
	@ManyToOne
	private User lastUpdateRegister;
	
	
	
	
	

	public User getCreateRegister() {	return createRegister;}
	public void setCreateRegister(User createRegister) {this.createRegister = createRegister;}
	
	public Calendar getCreateDate() {return createDate;}
	public void setCreateDate(Calendar createDate) {this.createDate = createDate;}
	
	public Calendar getLastUpdateDate() {return lastUpdateDate;}
	public void setLastUpdateDate(Calendar lastUpdateDate) {this.lastUpdateDate = lastUpdateDate;}

	public User getLastUpdateRegister() {return lastUpdateRegister;}
	public void setLastUpdateRegister(User lastUpdateRegister) {this.lastUpdateRegister = lastUpdateRegister;}


	

}
