package vip.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import vip.kernel.domain.PersistentObjectRegister_;

@Generated(value="Dali", date="2016-08-08T18:59:18.225-0300")
@StaticMetamodel(Operator.class)
public class Operator_ extends PersistentObjectRegister_ {
	public static volatile SingularAttribute<Operator, String> name;
	public static volatile SingularAttribute<Operator, Double> perc_credito;
	public static volatile SingularAttribute<Operator, Double> perc_debito;
	public static volatile SingularAttribute<Operator, Boolean> ativo;
}
