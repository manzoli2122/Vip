package vip.secretariat.domain;



public enum FormOfPayment {
	
	Dinheiro("Dinheiro"), 
	Cartao_Credito("Cartao Credito"), 
	Cartao_Debito("Cartao Debito"), 
	Cheque("Cheque"); 
	
	
	private final String label;

	private FormOfPayment(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
    }

}
