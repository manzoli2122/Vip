package vip.core.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-18T11:23:47.720-0300")
@StaticMetamodel(Expense.class)
public class Expense_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Expense, Date> createDate;
	public static volatile SingularAttribute<Expense, Double> valor;
	public static volatile SingularAttribute<Expense, User> register;
}
