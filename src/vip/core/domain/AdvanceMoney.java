package vip.core.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;




@Entity
@DiscriminatorValue("AdvanceMoney")
public class AdvanceMoney extends Expense implements Comparable<AdvanceMoney>{

	private static final long serialVersionUID = 1L;
		
	private String descricao;

	//@NotNull
	//@Temporal(TemporalType.TIMESTAMP)
	//protected Calendar takeDate;
	
	@NotNull
	@ManyToOne
	private User funcionario;

	@ManyToOne
	//@ManyToOne(cascade = CascadeType.PERSIST)
	private Salary salary;
	
	

	
	
	
	//public Calendar getTakeDate() {	return takeDate;}
	//public void setTakeDate(Calendar takeDate) {this.takeDate = takeDate;}
	
	public User getFuncionario() {	return funcionario;	}
	public void setFuncionario(User funcionario) {	this.funcionario = funcionario;	}
	
	public Salary getSalary() {return salary;}
	public void setSalary(Salary salary) {	this.salary = salary;}
	
	public String getDescricao() { return descricao;}
	public void setDescricao(String descricao) { this.descricao = descricao; }
	
	
	
	
	@Override
	public String toString() {
		return funcionario.getName() + " - " + valor + " - " + descricao ;
	}
	
	
	
	@Override
	public int compareTo(AdvanceMoney o) {
		return super.compareTo(o);
	}
	
	

}
