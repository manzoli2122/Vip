package vip.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import vip.secretariat.domain.EmployeeAttendance;

@Generated(value="Dali", date="2016-07-18T11:24:42.538-0300")
@StaticMetamodel(Salary.class)
public class Salary_ extends Expense_ {
	public static volatile SetAttribute<Salary, EmployeeAttendance> servicos;
	public static volatile SetAttribute<Salary, AdvanceMoney> vales;
	public static volatile SingularAttribute<Salary, User> funcionario;
}
