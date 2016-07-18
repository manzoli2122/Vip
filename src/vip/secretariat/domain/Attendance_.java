package vip.secretariat.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import vip.core.domain.User;

@Generated(value="Dali", date="2016-07-17T11:39:25.220-0300")
@StaticMetamodel(Attendance.class)
public class Attendance_ extends Income_ {
	public static volatile SetAttribute<Attendance, EmployeeAttendance> servicos;
	public static volatile SingularAttribute<Attendance, User> cliente;
}
