package vip.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import vip.core.application.ManageTasksService;
import vip.core.domain.Task;


@Named
@SessionScoped
public class ManageTasksController extends CrudController<Task>{

	private static final long serialVersionUID = 1L;

	@EJB
	private ManageTasksService manageTasksService;
	
	
	
	
	/*   CONSTRUTOR DA CLASSE */
	public ManageTasksController(){
		 viewPath = "/core/manageTask/";
	     bundleName = "msgsCore";
	}
	
	/* METODO OBRIGATORIO*/
	@Override
	protected CrudService<Task> getCrudService() {
		return manageTasksService;
	}

	

	/* METODO OBRIGATORIO*/
	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageTasks.filter.byName", "name", getI18nMessage(bundleName, "manageTasks.text.filter.byName")));
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
