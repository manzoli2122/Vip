package vip.core.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;
import vip.core.domain.Academic;
import vip.core.domain.AcademicType;
import vip.core.persistence.AcademicDAO;




@Named
@ApplicationScoped
public class CoreController implements Serializable{


	private static final long serialVersionUID = 1L;

	
	/** The DAO for Academic objects. */
	@EJB    	
	private AcademicDAO academicDAO;
	
	
	
	
	/** JSF Converter for Academic objects. */
	private PersistentObjectConverterFromId<Academic> academicConverter;

	
	
	/** Getter for AcademicConverter */
	public Converter getAcademicConverter() {
		if (academicConverter == null) {
			academicConverter = new PersistentObjectConverterFromId<Academic>(academicDAO);
			
		}
		return academicConverter;
	}
	
	
	

	
	
	
	public List<Academic> getTeachers(){
		List<Academic> lista = academicDAO.retrieveAll();
		List<Academic> teacher = new ArrayList<Academic>();
		for(Academic academic : lista){
			Iterator<AcademicType> it = academic.getAcademicTypes().iterator();
			while(it.hasNext()){
				if(it.next().equals(AcademicType.Teacher)){
					teacher.add(academic);
				}
			}
		}
		return teacher;
	}
	
	
	public List<Academic> getAlumnis(){
		List<Academic> lista = academicDAO.retrieveAll();
		List<Academic> alumni = new ArrayList<Academic>();
		for(Academic academic : lista){
			Iterator<AcademicType> it = academic.getAcademicTypes().iterator();
			while(it.hasNext()){
				if(it.next().equals(AcademicType.Alumni)){
					alumni.add(academic);
				}
			}
		}
		return alumni;
	}
	
	
	
	
	
	
	public String login(){
		return "/login/index.xhtml?faces-redirect=true" ;
	}
	
	
	
	
	public AcademicType[] getAcademicType() {
		return AcademicType.values();
	}
	
	
	
}
