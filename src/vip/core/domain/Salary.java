package vip.core.domain;

import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import vip.secretariat.domain.EmployeeAttendance;


@Entity
@DiscriminatorValue("Salary")
public class Salary extends Expense implements Comparable<Salary>{
	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="salary"  )
	private Set<EmployeeAttendance> servicos;
	
	@OneToMany(mappedBy="salary" )
	private Set<AdvanceMoney> vales;
	
	@NotNull
	@ManyToOne
	private User funcionario;
	
	

	@Override
	public int compareTo(Salary o) { 	return super.compareTo(o); }


	
	
	
	/* GETTERS AND SETTERS */
	public Set<EmployeeAttendance> getServicos() { return servicos; }
	public void setServicos(Set<EmployeeAttendance> servicos) { this.servicos = servicos; }

	public User getFuncionario() { 	return funcionario; }
	public void setFuncionario(User funcionario) { 	this.funcionario = funcionario; }

	public Set<AdvanceMoney> getVales() { 	return vales; 	}
	public void setVales(Set<AdvanceMoney> vales) {	this.vales = vales; }


	
	
}
