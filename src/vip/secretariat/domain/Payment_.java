package vip.secretariat.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-17T11:30:01.015-0300")
@StaticMetamodel(Payment.class)
public class Payment_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Payment, Date> createDate;
	public static volatile SingularAttribute<Payment, Double> valor;
	public static volatile SingularAttribute<Payment, Boolean> compensado;
	public static volatile SingularAttribute<Payment, Integer> parcelas;
	public static volatile SingularAttribute<Payment, Double> perc_cartao;
	public static volatile SingularAttribute<Payment, Income> income;
	public static volatile SingularAttribute<Payment, FormOfPayment> formOfPayment;
}
