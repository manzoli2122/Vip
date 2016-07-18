package vip.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-18T17:28:11.213-0300")
@StaticMetamodel(AdvanceMoney.class)
public class AdvanceMoney_ extends Expense_ {
	public static volatile SingularAttribute<AdvanceMoney, String> descricao;
	public static volatile SingularAttribute<AdvanceMoney, User> funcionario;
	public static volatile SingularAttribute<AdvanceMoney, Salary> salary;
}
