package vip.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import vip.core.application.ManageOperatorService;
import vip.core.domain.Operator;

@Named
@SessionScoped
public class ManageOperatorController extends CrudController<Operator>{

	
	private static final long serialVersionUID = 1L;

	@EJB
	private ManageOperatorService manageOperatorService;
	
	
	/*   CONSTRUTOR DA CLASSE */
	public ManageOperatorController(){
		 viewPath = "/core/manageOperator/";
	     bundleName = "msgsCore";
	}
	
	
	@Override
	protected CrudService<Operator> getCrudService() {
		return manageOperatorService;
	}

	@Override
	protected void initFilters() {
	}
	
	
	
	public String ativarDesativarTask(){
		selectedEntity.setAtivo(!selectedEntity.isAtivo());
		return save();
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
	
	
	
	

}
