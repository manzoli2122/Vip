package vip.core.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import vip.kernel.domain.PersistentObjectRegister;



@Entity
public class Operator extends  PersistentObjectRegister implements Comparable<Operator>{

	
	private static final long serialVersionUID = 1L;

	@NotNull
	private String name;
	
	@NotNull
	private Double perc_credito;
	
	@NotNull
	private Double perc_debito;
	
	@NotNull
	private boolean ativo;
	
	
	
	@Override
	public int compareTo(Operator o) {
		return super.compareTo(o);
	}



	public Double getPerc_credito() { return perc_credito;	}
	public void setPerc_credito(Double perc_credito) {this.perc_credito = perc_credito;	}

	public Double getPerc_debito() {return perc_debito;	}
	public void setPerc_debito(Double perc_debito) {this.perc_debito = perc_debito;	}

	public String getName() {return name;	}
	public void setName(String name) {this.name = name;	}

	public boolean isAtivo() {	return ativo;}
	public void setAtivo(boolean ativo) {this.ativo = ativo;}

	
	
}
