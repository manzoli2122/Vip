package vip.secretariat.controller;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import vip.secretariat.domain.FormOfPayment;


@Named
@ApplicationScoped
public class SecretariatController implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	
	
	public FormOfPayment[] getFormOfPayment() {
		return FormOfPayment.values();
	}

	

}
