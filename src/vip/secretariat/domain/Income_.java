package vip.secretariat.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import vip.core.domain.User;

@Generated(value="Dali", date="2016-07-17T11:25:57.011-0300")
@StaticMetamodel(Income.class)
public class Income_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Income, Date> createDate;
	public static volatile SingularAttribute<Income, Double> valor;
	public static volatile SingularAttribute<Income, User> register;
	public static volatile SetAttribute<Income, Payment> pagamentos;
}
