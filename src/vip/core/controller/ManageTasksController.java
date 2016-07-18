package vip.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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

	
	
}
