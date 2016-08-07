package vip.core.controller;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import vip.core.application.RelatorioDiarioService;
import vip.core.domain.User;
import vip.secretariat.domain.Attendance;





@Named
@SessionScoped
public class RelatorioDiarioController extends JSFController {


	private static final long serialVersionUID = 1L;
	
	private String viewPath;
	
	private Calendar dia;
	
	private User funcionario;
	
	
	@EJB
	private RelatorioDiarioService relatorioDiarioService;
	
	private List<Attendance> listaAtendimentos;
	
	
	
	/**   CONSTRUTOR DA CLASSE */
	public RelatorioDiarioController(){
		 viewPath = "/core/relatorioDiario/";
	     bundleName = "msgsCore";
	}


	
	public String buscar(){
		listaAtendimentos = relatorioDiarioService.buscarByDate(dia, dia);
		return getViewPath() + "list.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	
	public String list(){
		listaAtendimentos = null;
		dia = Calendar.getInstance();
		return getViewPath() + "index.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	
	
	
	
	public String salvarRelatorioDiario(){
		try{
			relatorioDiarioService.salvarRelatorioDiario(listaAtendimentos);
		}catch(Exception e){
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_ERROR, getBundlePrefix() + ".salvar.delete", "");
			return null;
		}
		return getViewPath() + "index.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	
	
	
	
	
	
	
	
	public boolean getFacesRedirect() {return true;}
	
	public String getViewPath() {return viewPath;}

	public Calendar getDia() {return dia;}
	public void setDia(Calendar dia) {this.dia = dia;}

	public List<Attendance> getListaAtendimentos() {return listaAtendimentos;}
	public void setListaAtendimentos(List<Attendance> listaAtendimentos) {this.listaAtendimentos = listaAtendimentos;}

	public User getFuncionario() {return funcionario;	}
	public void setFuncionario(User funcionario) {	this.funcionario = funcionario;	}


}
