package vip.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import vip.core.application.ManageUsersService;
import vip.core.domain.User;
import vip.core.domain.UserType;
import vip.people.domain.Gender;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;

@Named
@SessionScoped
public class ManageUsersController extends CrudController<User>{

	private static final long serialVersionUID = 1L;
	
	
	private static final Logger logger = Logger.getLogger(ManageUsersController.class.getCanonicalName());
	
	
	@EJB
	private ManageUsersService manageUsersService;
	
	
	private List<UserType> userTypes;
	
	
	
	
	
	
	
	/**   CONSTRUTOR DA CLASSE */
	public ManageUsersController(){
		 viewPath = "/core/manageUsers/";
	     bundleName = "msgsCore";
	}
	

	@Override
	protected CrudService<User> getCrudService() {
		return manageUsersService;
	}
	

	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageUsers.filter.byName", "name", getI18nMessage(bundleName, "manageUsers.text.filter.byName")));
	}

	
	
	
	@Override
	protected void prepEntity() {
		selectedEntity.setUserTypes(userTypes);
	}
	
	
	
	@Override
	protected void checkSelectedEntity() {
		userTypes = new ArrayList<UserType>() ;
		if(selectedEntity.getUserTypes()!=null)
			userTypes.addAll(selectedEntity.getUserTypes());		
	}
	 

	@Override
	protected User createNewEntity() {
		userTypes = new ArrayList<UserType>() ;
		return super.createNewEntity();
	}
	
	
	public Gender[] getGender() {
		return Gender.values();
	}


	public List<UserType> getUserTypes() {
		return userTypes;
	}


	public void setUserTypes(List<UserType> userTypes) {
		this.userTypes = userTypes;
	}
	
	
	
	
	
	
	
	
	/** @see br.ufes.inf.nemo.util.ejb3.controller.CrudController#save() */
	@Override
	public String save() {
		try{
			return super.save();
		}
		catch(Exception e){
			selectedEntity.setId(null);
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
