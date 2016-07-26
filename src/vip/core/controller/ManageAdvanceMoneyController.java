package vip.core.controller;

import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.MultipleChoiceFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import vip.core.application.ManageAdvanceMoneyService;
import vip.core.domain.AdvanceMoney;
import vip.core.domain.User;
import vip.kernel.application.SessionInformation;


@Named
@SessionScoped
public class ManageAdvanceMoneyController extends CrudController<AdvanceMoney>{

	private static final long serialVersionUID = 1L;

	@EJB
	private ManageAdvanceMoneyService manageValeService;
	
	@EJB
	private SessionInformation sessionInformation;
	
	@Inject
	private CoreController coreController;
	
	
	
	
	
	/*   CONSTRUTOR DA CLASSE */
	public ManageAdvanceMoneyController(){
		 viewPath = "/core/manageAdvanceMoney/";
	     bundleName = "msgsCore";
	}
	
	
	/* METODO OBRIGATORIO */
	@Override
	protected CrudService<AdvanceMoney> getCrudService() {
		return manageValeService;
	}
	

	/* METODO OBRIGATORIO */
	@Override
	protected AdvanceMoney createNewEntity() {
		AdvanceMoney advanceMoney = new AdvanceMoney();
		advanceMoney.setCreateDate(Calendar.getInstance());
		return advanceMoney;
	}

	
	
	
	
	
	
	/** @see br.ufes.inf.nemo.util.ejb3.controller.CrudController#save() */
	@Override
	public String save() {
		try{
			return super.save();
		}
		catch(Exception e){
			//selectedEntity.setId(null);
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_ERROR, getBundlePrefix() + ".error.save" , summarizeSelectedEntity()  );
			//selectedEntity = createNewEntity();
			return null;
		}
	}
	
	
	
	
	/** @see br.ufes.inf.nemo.util.ejb3.controller.CrudController#delete() */
	@Override
	public String delete() {
		try{
			return super.delete();
		}
		catch(Exception e){
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_ERROR, getBundlePrefix() + ".error.delete", summarizeSelectedEntity());
			cancelDeletion();
			return null;
		}
	}
	
	
	
	
	
	/* METODO OBRIGATORIO */
	@Override
	protected void initFilters() {
		addFilter(
				new MultipleChoiceFilter<User>("manageUser.filter.byFuncionario", "funcionario", "Procurar Por Funcionario" ,getEmployees() ,getMap()));	
	}
	
	
	/*	FUNCOES PARA O FILTRO */
	private Map<String, String> getMap(){
		Map<String, String> map1 = new LinkedHashMap<String, String>() ;
		User user;
		Iterator<User> iter = getEmployees().iterator();
		while(iter.hasNext()){
			user = iter.next();
			map1.put(user.getId().toString(), user.getName());
		}
		return map1;
	}
	
	/*	FUNCOES PARA O FILTRO */
	private List<User> getEmployees(){
		return coreController.getEmployees();
	}

	
}
