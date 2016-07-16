package vip.core.domain;




public enum AcademicType {

	Admin("Admin"),
	Alumni("Alumni"),
	Researcher("Researcher"),
	Student("Student"),
	Teacher("Teacher");
	
	
	private final String label;

	private AcademicType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
    }
	
	
	
}
