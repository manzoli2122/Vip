package vip.secretariat.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import vip.core.domain.User;

@Generated(value="Dali", date="2016-07-21T16:10:34.695-0300")
@StaticMetamodel(Attendance.class)
public class Attendance_ extends Income_ {
	public static volatile SingularAttribute<Attendance, User> cliente;
	public static volatile ListAttribute<Attendance, EmployeeAttendance> employeeAttendances;
}
