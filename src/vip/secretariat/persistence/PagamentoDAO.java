package vip.secretariat.persistence;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import vip.secretariat.domain.Payment;

@Local
public interface PagamentoDAO  extends BaseDAO<Payment>{

	

	List<Payment> retrieveCartao(Date init, Date fim, int parcela);

	List<Payment> retrieveCartaoDiario(Date dia);

	List<Payment> retrieveDinheiroDiario(Date date);

	List<Payment> retrieveDebitoDiario(Date date);

	List<Payment> retrieveChequeDiario(Date date);
	
	
}
