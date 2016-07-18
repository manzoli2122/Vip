package vip.core.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-17T01:07:31.633-0300")
@StaticMetamodel(Task.class)
public class Task_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Task, String> name;
	public static volatile SingularAttribute<Task, Double> valor;
	public static volatile SingularAttribute<Task, Double> porcentagemFuncionario;
}
