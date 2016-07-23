package vip.secretariat.domain;

import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import vip.core.domain.User;


@Entity
@DiscriminatorValue("Attendance")
public class Attendance  extends Income implements Comparable<Attendance>{

	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne
	private User cliente;
	
	@NotNull
	@OneToMany(mappedBy="attendance" , cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmployeeAttendance> employeeAttendances;
	
	
	
	
	@Override
	public int compareTo(Attendance o) {
		return super.compareTo(o);
	}
	
	
	@Override
	public String toString(){
		String logstr = String.valueOf(getId());
		logstr += ", Cliente = "+cliente;
		
		if(employeeAttendances != null){
			logstr += ", Serviços → ";
			Iterator<EmployeeAttendance> ite = employeeAttendances.iterator();
			while(ite.hasNext()){
				logstr +=  (ite.next().toString() + "; ");
			}
		}
		return logstr;
	}
	
	
	public void calcularValor(){
		Double valor = 0.0;
		try{
			Iterator<EmployeeAttendance> iterator = employeeAttendances.iterator();
			
			while(iterator.hasNext()){			
				valor += iterator.next().getValorComDesconto();
			}
		}
		catch(Exception e){}
		this.setValor(valor);
	}
	
	
	public Double getValorPayments(){
		double valor= 0.0;
		try{
			Iterator<Payment> pagamentos = payments.iterator();
			while(pagamentos.hasNext()){
				valor+=pagamentos.next().getValor();
			}
		}
		catch(Exception e){}
		return valor;
	}
		
	
	
	
	
	/* GETTERS AND SETTERS*/
	
	public List<EmployeeAttendance> getEmployeeAttendances() { return employeeAttendances; }
	public void setEmployeeAttendances(List<EmployeeAttendance> employeeAttendance) { this.employeeAttendances = employeeAttendance; }
		
	public User getCliente() { 	return cliente; }
	public void setCliente(User cliente) { 	this.cliente = cliente; }
	
}
