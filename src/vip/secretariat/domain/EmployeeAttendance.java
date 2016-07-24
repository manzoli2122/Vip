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
	private Double desconto;
	
	@NotNull
	@ManyToOne
	private Task task;
	
	@NotNull
	@ManyToOne
	private User employee;
		
	@NotNull
	@ManyToOne
	private Attendance attendance;
	
	//@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne
	private Salary salary;

	
	
	
	
	
	

	@Override
	public int compareTo(EmployeeAttendance o) {
		return super.compareTo(o);
	}

	@Override
	public String toString() {
		return  task.toString() +" feito por "+ employee.toString();
	}
	
	public Double getComissao(){ return ( getValorComDesconto() * getPorcentagemFuncionario() ); }
	
	public Double getValorComDesconto() { return (task.getValor()-desconto);  }	
	
	public Double getPorcentagemFuncionario() { return task.getPorcentagemFuncionario(); }
	
	public boolean isSalarioCalculado() { return salary!=null; }
	
	
	
	
	
	
	
	
	
	/* GETTERS AND SETTERS */

	public User getEmployee() { 	return employee; }
	public void setEmployee(User employee) { this.employee = employee; }

	public Attendance getAtendance() { return attendance; }
	public void setAttendance(Attendance attendance) { this.attendance = attendance; }

	public Task getTask() {	return task; }
	public void setTask(Task task) {this.task = task;}

	public Salary getSalary() { return salary;}
	public void setSalary(Salary salary) { this.salary = salary; }
	
	public Double getDesconto() { return desconto; 	}
	public void setDesconto(Double desconto) { 	this.desconto = desconto;	}
	
	
}
