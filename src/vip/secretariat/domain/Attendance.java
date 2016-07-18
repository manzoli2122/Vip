package vip.secretariat.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import vip.core.domain.User;


@Entity
@DiscriminatorValue("ATENDIMENTO")
public class Attendance  extends Income implements Comparable<Attendance>{

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy="atendimento" , cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<EmployeeAttendance> servicos;
	
	@NotNull
	@ManyToOne
	private User cliente;

	
	
	
	
	
	@Override
	public int compareTo(Attendance o) {
		return super.compareTo(o);
	}
	
	
	@Override
	public String toString(){
		String logstr = String.valueOf(getId());
		logstr += ", Cliente = "+cliente;
		
		if(servicos != null){
			logstr += ", Serviços → ";
			Iterator<EmployeeAttendance> ite = servicos.iterator();
			while(ite.hasNext()){
				logstr +=  (ite.next().toString() + "; ");
			}
		}
		
		if(pagamentos != null){
			logstr += ", Pagamentos → ";
			Iterator<Payment> ite = pagamentos.iterator();
			while(ite.hasNext()){
				logstr +=  (ite.next().toString() + "; ") ;
			}
		}
		
		return logstr;
	}
	
	
	public Double calcularValorServicos(){
		Double valor = 0.0;
		if(servicos !=null) {
			Iterator<EmployeeAttendance> ite = servicos.iterator();
			while (ite.hasNext()) {
				valor += ite.next().getValor();			
			}
		}
		this.setValor(valor);
		return valor;
	}
	
	
	public Double calcularValorPagamentos(){
		Double valor = 0.0;
		if(pagamentos !=null) {
			Iterator<Payment> ite = pagamentos.iterator();
			while (ite.hasNext()) {
				valor += ite.next().getValor();			
			}
		}
		return valor;
	}
	
	
	
	
	
	
	/* GETTERS AND SETTERS*/
	
	public List<EmployeeAttendance> getListServicos() { return new ArrayList<EmployeeAttendance>(servicos); }
	public Set<EmployeeAttendance> getServicos() { return servicos; }
	public void setServicos(Set<EmployeeAttendance> servicos) { this.servicos = servicos; }

	public List<Payment> getListPagamentos() { return new ArrayList<Payment>(pagamentos); }
		
	public User getCliente() { 	return cliente; }
	public void setCliente(User cliente) { 	this.cliente = cliente; }
	
}
