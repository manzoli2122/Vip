package vip.core.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-16T01:38:00.818-0300")
@StaticMetamodel(VipConfiguration.class)
public class VipConfiguration_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<VipConfiguration, Date> creationDate;
	public static volatile SingularAttribute<VipConfiguration, String> institutionAcronym;
	public static volatile SingularAttribute<VipConfiguration, String> smtpServerAddress;
	public static volatile SingularAttribute<VipConfiguration, Integer> smtpServerPort;
	public static volatile SingularAttribute<VipConfiguration, String> smtpUsername;
	public static volatile SingularAttribute<VipConfiguration, String> smtpPassword;
	public static volatile SingularAttribute<VipConfiguration, Date> updateDataDate;
}