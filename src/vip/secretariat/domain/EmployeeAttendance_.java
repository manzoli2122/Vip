package vip.secretariat.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import vip.core.domain.Salary;
import vip.core.domain.Task;
import vip.core.domain.User;

@Generated(value="Dali", date="2016-08-08T18:59:33.172-0300")
@StaticMetamodel(EmployeeAttendance.class)
public class EmployeeAttendance_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<EmployeeAttendance, Double> desconto;
	public static volatile SingularAttribute<EmployeeAttendance, Task> task;
	public static volatile SingularAttribute<EmployeeAttendance, User> employee;
	public static volatile SingularAttribute<EmployeeAttendance, Attendance> attendance;
	public static volatile SingularAttribute<EmployeeAttendance, Salary> salary;
}
