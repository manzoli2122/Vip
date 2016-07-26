package vip.secretariat.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import vip.kernel.domain.PersistentObjectRegister;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "class", discriminatorType = DiscriminatorType.STRING )
public abstract class Income extends  PersistentObjectRegister {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	protected Double valor;
	
	
	@NotNull
	@OneToMany(mappedBy="income" , cascade = CascadeType.ALL, orphanRemoval = true)
	protected List<Payment> payments;
	
	
	
	
	public List<Payment> getPayments() {return payments; }
	public void setPayments(List<Payment> payments) { this.payments = payments; }
	
	public Double getValor() { 	return valor; }
	public void setValor(Double valor) { this.valor = valor; }

	


}
