package vip.core.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;


@Entity
public class Task extends  PersistentObjectSupport implements Comparable<Task>{

	private static final long serialVersionUID = 1L;

	
	@NotNull
	@Size(max = 50)
	private String name;
	
	@NotNull
	private Double valor;
	
	@NotNull
	private Double porcentagemFuncionario;
	
	
	
	
	
	public String log(){
		String logstr = String.valueOf(getId());
		logstr += (", "+ name) ;
		logstr += (", "+ valor) ;
		logstr += (", "+ porcentagemFuncionario +"%") ;	
		return logstr;
	}
	
	
	@Override
	public String toString() { return name + ", R$ "+valor; }
	
	@Override
	public int compareTo(Task o) { return super.compareTo(o); }
	
	
	
	/* GETTERS AND SETTERS */
	
	public String getName() { return name; 	}
	public void setName(String name) { this.name = name; }
	
	public Double getValor() { return valor; }
	public void setValor(Double valor) { this.valor = valor; }
	
	public Double getPorcentagemFuncionario() { return porcentagemFuncionario; }
	public void setPorcentagemFuncionario(Double porcentagemFuncionario) { this.porcentagemFuncionario = porcentagemFuncionario; }

	
	
}
