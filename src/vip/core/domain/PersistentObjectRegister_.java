package vip.core.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-23T08:21:24.915-0300")
@StaticMetamodel(PersistentObjectRegister.class)
public class PersistentObjectRegister_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<PersistentObjectRegister, User> createRegister;
	public static volatile SingularAttribute<PersistentObjectRegister, Calendar> createDate;
	public static volatile SingularAttribute<PersistentObjectRegister, Calendar> lastUpdateDate;
	public static volatile SingularAttribute<PersistentObjectRegister, User> lastUpdateRegister;
}
