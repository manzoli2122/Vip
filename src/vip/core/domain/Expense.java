package vip.core.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import vip.kernel.domain.PersistentObjectRegister;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "class", discriminatorType = DiscriminatorType.STRING )
public abstract class Expense extends  PersistentObjectRegister{


	private static final long serialVersionUID = 1L;

	@NotNull
	protected Double valor;	
	

	public Double getValor() { 	return valor; }
	public void setValor(Double valor) { this.valor = valor; }

}
