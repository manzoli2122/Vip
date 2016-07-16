package vip.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import vip.core.application.ManageAcademicsService;
import vip.core.domain.Academic;
import vip.core.domain.AcademicType;
import vip.people.domain.Gender;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;

@Named
@SessionScoped
public class ManageAcademicsController extends CrudController<Academic>{

	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	
	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageAcademicsController.class.getCanonicalName());
	
	
	/** The "Manage Academic" service. */
	@EJB
	private ManageAcademicsService manageAcademicsService;
	
	private List<AcademicType> academicTypes;
	
	
	/**   CONSTRUTOR DA CLASSE */
	public ManageAcademicsController(){
		 viewPath = "/core/manageAcademics/";
	     bundleName = "msgsCore";
	}
	

	@Override
	protected CrudService<Academic> getCrudService() {
		return manageAcademicsService;
	}

	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageAcademics.filter.byName", "name", getI18nMessage(bundleName, "manageAcademics.text.filter.byName")));
	}

	
	
	
	@Override
	protected void prepEntity() {
		selectedEntity.setAcademicTypes(academicTypes);
	}
	
	@Override
	protected void checkSelectedEntity() {
		academicTypes = new ArrayList<AcademicType>() ;
		if(selectedEntity.getAcademicTypes()!=null)
			academicTypes.addAll(selectedEntity.getAcademicTypes());		
	}
	 

	@Override
	protected Academic createNewEntity() {
		academicTypes = new ArrayList<AcademicType>() ;
		return super.createNewEntity();
	}
	
	
	public Gender[] getGender() {
		return Gender.values();
	}
	
	
	public List<AcademicType> getAcademicTypes() {
		return academicTypes;
	}

	public void setAcademicTypes(List<AcademicType> academicTypes) {
		this.academicTypes = academicTypes;
	}
	
}
