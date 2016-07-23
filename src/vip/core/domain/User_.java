package vip.core.domain;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import vip.people.domain.Person_;

@Generated(value="Dali", date="2016-07-23T09:22:53.688-0300")
@StaticMetamodel(User.class)
public class User_ extends Person_ {
	public static volatile SingularAttribute<User, String> shortName;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> password;
	public static volatile ListAttribute<User, UserType> userTypes;
	public static volatile SingularAttribute<User, Calendar> lastUpdateDate;
	public static volatile SingularAttribute<User, Calendar> lastLoginDate;
	public static volatile SingularAttribute<User, Calendar> creationDate;
}
