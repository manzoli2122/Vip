package vip.secretariat.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;
import vip.core.domain.Salary;
import vip.core.domain.Task;
import vip.core.domain.User;

@Entity
public class EmployeeAttendance extends  PersistentObjectSupport implements Comparable<EmployeeAttendance>{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private boolean salarioCalculado;
	
	@NotNull
	private Double valor;
	
	private Double desconto;
	
	@NotNull
	private Double porcentagemFuncionario;
	
	
	
	@NotNull
	@ManyToOne
	private Task task;
	
	@NotNull
	@ManyToOne
	private User funcionario;
		
	@NotNull
	@ManyToOne
	private Attendance atendimento;
	
	@ManyToOne
	private Salary salario;

	
	
	
	
	
	
	
	
	
	
	public Salary getSalario() {
		return salario;
	}

	public void setSalario(Salary salario) {
		this.salario = salario;
	}

	@Override
	public int compareTo(EmployeeAttendance o) {
		return super.compareTo(o);
	}

	@Override
	public String toString() {
		return  task.toString() +" feito por "+ funcionario.toString();
	}
	
	public Double getComissao(){ return ( getValor() * getPorcentagemFuncionario() ); }
	
	
	/* GETTERS AND SETTERS */

	public Task getTask() {	return task; }
	public void setTask(Task task) {
		if(!salarioCalculado||task==null){
			this.task = task; 
			valor = task.getValor();
			porcentagemFuncionario =task.getPorcentagemFuncionario();
		}
		this.task = task;
	}

	public User getFuncionario() { 	return funcionario; }
	public void setFuncionario(User funcionario) { if(!salarioCalculado||funcionario==null) this.funcionario = funcionario; }

	public boolean isSalarioCalculado() { return salarioCalculado; }
	public void setSalarioCalculado(boolean salarioCalculado) { this.salarioCalculado = salarioCalculado; }

	public Attendance getAtendimento() { return atendimento; }
	public void setAtendimento(Attendance atendimento) { if(!salarioCalculado||atendimento==null) this.atendimento = atendimento; }
	
	public Double getValor() { if(desconto!=null)return(valor - desconto); return valor; }
	public void setValor(Double valor) { if(!salarioCalculado||valor==null) this.valor = valor; }
	
	public Double getPorcentagemFuncionario() { return porcentagemFuncionario; }
	public void setPorcentagemFuncionario(Double porcentagemFuncionario) { 
		if(!salarioCalculado||porcentagemFuncionario==null)
			this.porcentagemFuncionario = porcentagemFuncionario;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	
	
}
