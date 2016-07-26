package vip.secretariat.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;


@Entity
public class Payment extends  PersistentObjectSupport  implements Comparable<Payment>{

	private static final long serialVersionUID = 1L;

	private boolean compensado;
	
	private int parcelas;
	
	@NotNull
	protected Double valor;
	
	@NotNull
	@ManyToOne
	private Income income;
	
	
	@NotNull
	@Enumerated(EnumType.STRING) 
	private FormOfPayment formOfPayment;
	
	private Double perc_cartao;
	
	
	
	
	public boolean isCredito(){ return FormOfPayment.Cartao_Credito.equals(formOfPayment); }
	public boolean isDebito(){ return FormOfPayment.Cartao_Debito.equals(formOfPayment); }
	
	public  String toString(){ 
		String print = formOfPayment.getLabel()+ ", R$ " + valor;
		if(isCredito()){print+= (", em " + parcelas + " parcelas");	}
		return print; 
	}
		
	@Override
	public int compareTo(Payment o) {	return super.compareTo(o);}
	
	public Double getLiquido(){
		
		//DecimalFormat df = new DecimalFormat("0.##");
		//String teste;
		if(FormOfPayment.Cartao_Credito.equals(formOfPayment) || FormOfPayment.Cartao_Debito.equals(formOfPayment)){
			//teste = df.format((valor * perc_cartao)/parcelas);
			//teste = teste.replace(",",".");
			//return  Double.valueOf(teste) ;//  ((valor * perc_cartao)/parcelas) ;
			return (valor * perc_cartao)/parcelas;
		}
		
		else {
			//teste = df.format(valor);
			//teste = teste.replace(",",".");
			//return Double.valueOf(teste);
			return valor;
		}
	}
	
	
	/* GETTERS AND SETTERS  */
	
	public FormOfPayment getFormOfPayment() { 	return formOfPayment; 	}
	public void setFormOfPayment(FormOfPayment formOfPayment) { this.formOfPayment = formOfPayment; }
	
	public boolean isCompensado() { return compensado; 	}
	public void setCompensado(boolean compensado) { this.compensado = compensado; }
	
	public int getParcelas() { 	return parcelas; }
	public void setParcelas(int parcelas) { this.parcelas = parcelas; }
	
	public Double getValor() { return valor;}
	public void setValor(Double valor) {this.valor = valor;}

	public Income getIncome() {return income;}
	public void setIncome(Income income) {	this.income = income;}

	public Double getPerc_cartao() { return perc_cartao; }
	public void setPerc_cartao(Double perc_cartao) { this.perc_cartao = perc_cartao;}
	
	
}
