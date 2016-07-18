package vip.core.domain;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import vip.secretariat.domain.EmployeeAttendance;


@Entity
@DiscriminatorValue("SALARY")
public class Salary extends Expense implements Comparable<Salary>{

	
	private static final long serialVersionUID = 1L;

	
	@OneToMany(mappedBy="salario" , cascade = CascadeType.MERGE )
	private Set<EmployeeAttendance> servicos;
	
	@OneToMany(mappedBy="salary" , cascade = CascadeType.MERGE )
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


	public Double calcularValorSalario(){
		Double valor = calcularServicos() - calcularVales();
		this.setValor(valor);
		return valor;
	}
	
	public Double calcularServicos(){
		Double valor = 0.0;
		if(servicos !=null) {
			Iterator<EmployeeAttendance> ite = servicos.iterator();
			while (ite.hasNext()) {
				valor += ite.next().getComissao();			
			}
		}
		return valor;
	}
	
	
	public Double calcularVales(){
		Double valor = 0.0;
		if(vales != null){
			Iterator<AdvanceMoney> it = vales.iterator();
			while (it.hasNext()) {
				valor += it.next().getValor();			
			}
		}
		return valor;
	}
	
	
	
}
