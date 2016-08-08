package vip.secretariat.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import vip.core.domain.Operator;

@Generated(value="Dali", date="2016-08-08T18:59:33.186-0300")
@StaticMetamodel(Payment.class)
public class Payment_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Payment, Boolean> compensado;
	public static volatile SingularAttribute<Payment, Integer> parcelas;
	public static volatile SingularAttribute<Payment, Double> valor;
	public static volatile SingularAttribute<Payment, Income> income;
	public static volatile SingularAttribute<Payment, Operator> operator;
	public static volatile SingularAttribute<Payment, FormOfPayment> formOfPayment;
	public static volatile SingularAttribute<Payment, Double> perc_cartao;
}
