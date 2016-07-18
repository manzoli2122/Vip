package vip.secretariat.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;
import vip.core.domain.User;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "class", discriminatorType = DiscriminatorType.STRING )
public abstract class Income extends  PersistentObjectSupport {

	private static final long serialVersionUID = 1L;
	
	/*  */
	@NotNull
	@Temporal(TemporalType.DATE)
	protected Date createDate;
	
	@NotNull
	protected Double valor;
	
	@NotNull
	@ManyToOne
	protected User register;
	
	@OneToMany(mappedBy="income" , cascade = CascadeType.ALL, orphanRemoval = true)
	protected Set<Payment> pagamentos;

	
	
	
	public Date getCreateDate() { return createDate; 	}
	public void setCreateDate(Date createDate) { this.createDate = createDate; }

	public Double getValor() { 	return valor; }
	public void setValor(Double valor) { this.valor = valor; }

	public User getRegister() { return register; }
	public void setRegister(User register) { this.register = register; }

	public Set<Payment> getPagamentos() { return pagamentos; }
	public void setPagamentos(Set<Payment> pagamentos) { this.pagamentos = pagamentos; }
	


}