package vip.kernel.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import vip.kernel.application.ManageConfigurationService;
import vip.kernel.domain.VipConfiguration;





@Named
@SessionScoped
public class ManageConfigurationController extends CrudController<VipConfiguration>{

	
	private static final long serialVersionUID = 1L;

	
	
	@EJB
	private ManageConfigurationService  manageConfigurationService ;
	
	
	/*   CONSTRUTOR DA CLASSE */
	public ManageConfigurationController(){
		 viewPath = "/core/manageConfiguration/";
	     bundleName = "msgsCore";
	}
	
	
	
	
	
	
	@Override
	protected CrudService<VipConfiguration> getCrudService() {
		return manageConfigurationService;
	}

	@Override
	protected void initFilters() {
	}
	
	
	/** @see br.ufes.inf.nemo.util.ejb3.controller.CrudController#save() */
	@Override
	public String save() {
		try{
			return super.save();
		}
		catch(Exception e){
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_ERROR, getBundlePrefix() + ".error.save" , summarizeSelectedEntity()  );
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
