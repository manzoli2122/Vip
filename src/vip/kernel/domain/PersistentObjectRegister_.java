package vip.kernel.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import vip.core.domain.User;

@Generated(value="Dali", date="2016-08-08T19:16:26.093-0300")
@StaticMetamodel(PersistentObjectRegister.class)
public class PersistentObjectRegister_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<PersistentObjectRegister, User> createRegister;
	public static volatile SingularAttribute<PersistentObjectRegister, Calendar> createDate;
	public static volatile SingularAttribute<PersistentObjectRegister, Calendar> lastUpdateDate;
	public static volatile SingularAttribute<PersistentObjectRegister, User> lastUpdateRegister;
}
