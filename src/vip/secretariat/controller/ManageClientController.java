package vip.secretariat.controller;

import java.util.Calendar;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import vip.core.domain.User;
import vip.secretariat.application.ManageClientService;



@Named
@SessionScoped
public class ManageClientController extends CrudController<User> {
	
	private static final long serialVersionUID = 1L;


	@EJB
	private ManageClientService manageClienteService;
	
	
	
	
	
	/**   CONSTRUTOR DA CLASSE */
	public ManageClientController(){
		 viewPath = "/secretariat/manageClient/";
	     bundleName = "msgsCore";
	}
	
	
	@Override
	protected CrudService<User> getCrudService() {
		return manageClienteService;
	}

	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageClient.filter.byName", "name", getI18nMessage(bundleName, "manageUsers.text.filter.byName")));
	}
	
	
	
	
	
	@Override
	protected User createNewEntity() {
		User user = new User();
		user.setBirthDate(Calendar.getInstance());
		return user;
	}
	
	
	
	
	
	
	
	
	@Override
	protected void prepEntity() {
		suggestShortName();
	}
	
	
	
	
	
	
	
	
	
	
	public void suggestShortName() {
		// If the name was filled and the short name is still empty, suggest the first name as short name.
		String name = selectedEntity.getName();
		String shortName = selectedEntity.getShortName();
		
		if ((name != null) && ((shortName == null) || (shortName.length() == 0))) {
			int idx = name.indexOf(" ");
			selectedEntity.setShortName((idx == -1) ? name : name.substring(0, idx).trim());
		}
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
