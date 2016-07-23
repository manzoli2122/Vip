package vip.core.domain;


public enum UserType {

	Admin("Administrador"),
	Employee("Funcionario"),
	Cliente("Cliente");
	
	private final String label;

	private UserType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
    }
	
	
	
}
