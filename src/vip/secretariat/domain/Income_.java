package vip.secretariat.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import vip.core.domain.User;

@Generated(value="Dali", date="2016-07-21T18:45:40.497-0300")
@StaticMetamodel(Income.class)
public class Income_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Income, Calendar> createDate;
	public static volatile SingularAttribute<Income, Double> valor;
	public static volatile SingularAttribute<Income, User> register;
	public static volatile ListAttribute<Income, Payment> payments;
}
