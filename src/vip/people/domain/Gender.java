package vip.people.domain;


/**
 * Domain enum that represents people's gender .
 * 
 * 
 * @author Bruno Manzoli (manzoli2122@gmail.com)
 */
public enum Gender {
	Feminino("Feminino"),
	Masculino("Masculino");
	
	
	private final String label;

	private Gender(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
    }

}
