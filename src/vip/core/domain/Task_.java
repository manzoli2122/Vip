package vip.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-22T09:23:50.258-0300")
@StaticMetamodel(Task.class)
public class Task_ extends PersistentObjectRegister_ {
	public static volatile SingularAttribute<Task, String> name;
	public static volatile SingularAttribute<Task, Double> valor;
	public static volatile SingularAttribute<Task, Double> porcentagemFuncionario;
	public static volatile SingularAttribute<Task, Boolean> ativo;
}
