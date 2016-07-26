package vip.secretariat.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import vip.kernel.domain.PersistentObjectRegister_;

@Generated(value="Dali", date="2016-07-25T22:29:03.008-0300")
@StaticMetamodel(Income.class)
public class Income_ extends PersistentObjectRegister_ {
	public static volatile SingularAttribute<Income, Double> valor;
	public static volatile ListAttribute<Income, Payment> payments;
}
