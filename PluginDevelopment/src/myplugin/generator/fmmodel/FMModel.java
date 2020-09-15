package myplugin.generator.fmmodel;

import java.util.ArrayList;
import java.util.List;

/** FMModel: Singleton class. This is intermediate data structure that keeps metadata
 * extracted from MagicDraw model. Data structure should be optimized for code generation
 * using freemarker
 */

public class FMModel {
	
	private List<FMClass> classes = new ArrayList<FMClass>();
	
	//....
	/** @ToDo: Add lists of other elements, if needed */
	
	private List<FMClass> crudClasses = new ArrayList<FMClass>();
	
	private FMModel() {
		
	}
	
	private static FMModel model;
	
	public static FMModel getInstance() {
		if (model == null) {
			model = new FMModel();			
		}
		return model;
	}
	
	public List<FMClass> getClasses() {
		return classes;
	}
	public void setClasses(List<FMClass> classes) {
		this.classes = classes;
	}

	public List<FMClass> getCrudClasses() {
		return classes;
	}
	public void setCrudClasses(List<FMClass> crudClasses) {
		this.classes = crudClasses;
	}

}
