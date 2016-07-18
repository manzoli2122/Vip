package vip.secretariat.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.MultipleChoiceFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import vip.core.application.CoreInformation;
import vip.core.application.SessionInformation;
import vip.core.domain.User;
import vip.core.persistence.UserDAO;
import vip.secretariat.application.ManageAtendimentoService;
import vip.secretariat.domain.Attendance;
import vip.secretariat.domain.EmployeeAttendance;
import vip.secretariat.domain.Payment;
import vip.secretariat.domain.FormOfPayment;


@Named
@SessionScoped
public class ManageAtendimentoController extends CrudController<Attendance> {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManageAtendimentoService manageAtendimentoService;
	
	@EJB
	private SessionInformation sessionInformation;
	
	@EJB
	private CoreInformation coreInformation;
	
	@EJB 
	private UserDAO userDAO;
	
	private EmployeeAttendance servico;
		
	private Payment pagamento;
		
	
	
	
	
	
	/*	FUNCOES PARA O FILTRO */
	private Map<String, String> getMap(){
		Map<String, String> map1 = new LinkedHashMap<String, String>() ;
		User user;
		Iterator<User> iter = getLista().iterator();
		while(iter.hasNext()){
			user = iter.next();
			map1.put(user.getId().toString(), user.getName());
		}
		return map1;
	}
	/*	FUNCOES PARA O FILTRO */
	private List<User> getLista(){
		List<User> lista1;
		lista1 = userDAO.retrieveAll();
		return lista1;
	}
	
	
	
	
	/*   CONSTRUTOR DA CLASSE */
	public ManageAtendimentoController(){
		 viewPath = "/secretariat/manageAtendimento/";
	     bundleName = "msgsSalao";
	}

	
	/* METODO OBRIGATORIO */
	@Override
	protected CrudService<Attendance> getCrudService() {
		return manageAtendimentoService;
	}

	
	/* METODO OBRIGATORIO */
	@Override
	protected Attendance createNewEntity() {
		servico = null;
		pagamento = null;
		
		Attendance newEntity = new Attendance();
		newEntity.setCreateDate(new Date());
		newEntity.setRegister(sessionInformation.getCurrentUser());
		newEntity.setServicos(new TreeSet<EmployeeAttendance>());
		newEntity.setPagamentos(new TreeSet<Payment>());
		return newEntity;
	}

	
	/* METODO OBRIGATORIO */
	@Override
	protected void initFilters() {
		addFilter(
			new MultipleChoiceFilter<User>("manageUser.filter.byCliente", "cliente", "Por Cliente" ,getLista() ,getMap()));
		
	}

	
	
	public boolean isDeletavel(){
		if(selectedEntity==null)return false;
		Iterator<EmployeeAttendance> iter = selectedEntity.getListServicos().iterator();
		while(iter.hasNext()){
			if(iter.next().getSalario()!=null){
				return false;
			}
		}
		return true;
	}
	
	
	
	
	
	/* CHECA O ATENDIMENTO QUANDO BUSCA NO BANCO */
	@Override
	protected void checkSelectedEntity() {
		servico = null;
		if (selectedEntity.getServicos() == null)
			selectedEntity.setServicos(new TreeSet<EmployeeAttendance>());
		pagamento = null;
		if (selectedEntity.getPagamentos() == null)
			selectedEntity.setPagamentos(new TreeSet<Payment>());
	}

	
	/* PREPARA UM ATENDIMENTO ANTES DE SALVA-LO */
	@Override
	protected void prepEntity() {
		selectedEntity.setValor(selectedEntity.calcularValorServicos());
	}

	
	
	
	
	/* FUNCOES PARA SERVICO  */
	public EmployeeAttendance getServico() { return servico; }
	public void setServico(EmployeeAttendance servico) { this.servico = servico; }
	
	public void newServico() { 
		servico = new EmployeeAttendance(); 
		servico.setSalarioCalculado(false); 
		servico.setAtendimento(selectedEntity);
	}
	public void resetServico() { 	servico = null; }
	
	public void setFuncionario(User func) { if (servico != null) servico.setFuncionario(func); }
	public User getFuncionario() { return (servico== null) ? null : servico.getFuncionario(); }
	
	
	
	
	
	
	/* FUNCOES PARA PAGAMENTOS */
	public Payment getPagamento() { return pagamento; }
	public void setPagamento(Payment pagamento) { this.pagamento = pagamento; }
	
	public void newPagamento() { 	
		pagamento =new Payment(); 
		pagamento.setIncome(selectedEntity);
		pagamento.setCreateDate(selectedEntity.getCreateDate());
		pagamento.setParcelas(1);
		pagamento.setPerc_cartao(1.0);
		
	}
	public void resetPagamento() { 	pagamento = null;   }

	public void calcula_Porcentagem(){
		
		if(FormOfPayment.Cartao_Credito.equals(pagamento.getFormOfPayment())){
			pagamento.setPerc_cartao(coreInformation.getPerc_Credito());
		}
		else if(FormOfPayment.Cartao_Debito.equals(pagamento.getFormOfPayment())){
			pagamento.setPerc_cartao(coreInformation.getPerc_Debito());
		}
		
	}


	
	
}