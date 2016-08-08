package vip.kernel.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import vip.core.domain.User;

@Generated(value="Dali", date="2016-08-08T18:46:05.301-0300")
@StaticMetamodel(VipConfiguration.class)
public class VipConfiguration_ extends PersistentObjectRegister_ {
	public static volatile SingularAttribute<VipConfiguration, String> institutionAcronym;
	public static volatile SingularAttribute<VipConfiguration, User> administrador;
	public static volatile SingularAttribute<VipConfiguration, User> gerente;
	public static volatile SingularAttribute<VipConfiguration, String> smtpServerAddress;
	public static volatile SingularAttribute<VipConfiguration, Integer> smtpServerPort;
	public static volatile SingularAttribute<VipConfiguration, String> smtpUsername;
	public static volatile SingularAttribute<VipConfiguration, String> smtpPassword;
}
