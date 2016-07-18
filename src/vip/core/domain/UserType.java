package vip.core.domain;


public enum UserType {

	Admin("Administrador"),
	Employee("Funcionario");
	
	
	private final String label;

	private UserType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
    }
	
	
	
}
