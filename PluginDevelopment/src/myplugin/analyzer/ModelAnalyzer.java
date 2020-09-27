package myplugin.analyzer;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import myplugin.generator.fmmodel.ColumnProp;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMEnumeration;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.fmmodel.FMProperty;
import myplugin.generator.fmmodel.IdProp;
import myplugin.generator.fmmodel.ManyToMany;
import myplugin.generator.fmmodel.ManyToOne;
import myplugin.generator.fmmodel.OneToMany;

import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;


/** Model Analyzer takes necessary metadata from the MagicDraw model and puts it in 
 * the intermediate data structure (@see myplugin.generator.fmmodel.FMModel) optimized
 * for code generation using freemarker. Model Analyzer now takes metadata only for ejb code 
 * generation

 * @ToDo: Enhance (or completely rewrite) myplugin.generator.fmmodel classes and  
 * Model Analyzer methods in order to support GUI generation. */ 


public class ModelAnalyzer {	
	//root model package
	private Package root;
	
	//java root package for generated code
	private String filePackage;
	
	public ModelAnalyzer(Package root, String filePackage) {
		super();
		this.root = root;
		this.filePackage = filePackage;
	}

	public Package getRoot() {
		return root;
	}
	
	public void prepareModel() throws AnalyzeException {
		FMModel.getInstance().getClasses().clear();
		FMModel.getInstance().getCrudClasses().clear();
		//FMModel.getInstance().getEnumerations().clear();
		processPackage(root, filePackage);
	}
	
	private void processPackage(Package pack, String packageOwner) throws AnalyzeException {
		//Recursive procedure that extracts data from package elements and stores it in the 
		// intermediate data structure
		
		if (pack.getName() == null) throw  
			new AnalyzeException("Packages must have names!");
		
		String packageName = packageOwner;
		if (pack != root) {
			packageName += "." + pack.getName();
		}
		
		if (pack.hasOwnedElement()) {
			
			for (Iterator<Element> it = pack.getOwnedElement().iterator(); it.hasNext();) {
				Element ownedElement = it.next();
				if (ownedElement instanceof Class) {
					Class cl = (Class)ownedElement;
					FMClass fmClass = getClassData(cl, packageName);
					if(fmClass!=null) {
						FMModel.getInstance().getClasses().add(fmClass);
						if(fmClass.isGenerateCreate()==true || fmClass.isGenerateRead()==true || fmClass.isGenerateUpdate()==true || fmClass.isGenerateDelete()==true) {
							FMModel.getInstance().getCrudClasses().add(fmClass);
							//JOptionPane.showMessageDialog(null, fmClass.getClassName());
						}
					}
				}
				
				/*if (ownedElement instanceof Enumeration) {
					Enumeration en = (Enumeration)ownedElement;
					FMEnumeration fmEnumeration = getEnumerationData(en, packageName);
					FMModel.getInstance().getEnumerations().add(fmEnumeration);
				}		*/						
			}
			
			for (Iterator<Element> it = pack.getOwnedElement().iterator(); it.hasNext();) {
				Element ownedElement = it.next();
				if (ownedElement instanceof Package) {					
					Package ownedPackage = (Package)ownedElement;
					
					if(StereotypesHelper.getAppliedStereotypeByString(ownedPackage, "ProcessPackage")!=null) {
						processPackage(ownedPackage, packageName);
					}

				}
			}
			
			/** @ToDo:
			  * Process other package elements, as needed */ 
		}
	}
	
	private FMClass getClassData(Class cl, String packageName) throws AnalyzeException {
		if (cl.getName() == null) 
			throw new AnalyzeException("Classes must have names!");
		
		Stereotype tableSt = StereotypesHelper.getAppliedStereotypeByString(cl, "Table");
		
		//to do: logic when tableSt = null
		
		String tableName = "";
		boolean isEntity = false;
		
		List valuesEntity = StereotypesHelper.getStereotypePropertyValue(cl,tableSt,"entity");
		
		if (valuesEntity!=null && !valuesEntity.isEmpty()){
			isEntity = (boolean) valuesEntity.get(0);
		}
		
		if(isEntity) {
			tableName = StereotypesHelper.getStereotypePropertyValue(cl,tableSt,"name").get(0).toString();
		}else {
			tableName = null;
		}
		
		boolean generateCreate = false;
		boolean generateUpdate = false;
		boolean generateRead = false;
		boolean generateDelete = false;
		
		Stereotype crudSt = StereotypesHelper.getAppliedStereotypeByString(cl, "CRUD");
		if(crudSt != null) {
		    //create
			List createList = StereotypesHelper.getStereotypePropertyValue(cl, crudSt,"create");
		    if (createList!=null && !createList.isEmpty()){
		    	generateCreate = (boolean)createList.get(0);
		    }
		    
		    //update
			List updateList = StereotypesHelper.getStereotypePropertyValue(cl, crudSt,"update");
		    if (updateList!=null && !updateList.isEmpty()){
		    	generateUpdate = (boolean)updateList.get(0);
		    }
		    
		    //read
			List readList = StereotypesHelper.getStereotypePropertyValue(cl, crudSt,"read");
		    if (readList!=null && !readList.isEmpty()){
		    	generateRead = (boolean)readList.get(0);
		    }
		    
		    //delete
			List deleteList = StereotypesHelper.getStereotypePropertyValue(cl, crudSt,"delete");
		    if (deleteList!=null && !deleteList.isEmpty()){
		    	generateDelete = (boolean)deleteList.get(0);
		    }
		}
			
		FMClass fmClass = new FMClass(tableName,cl.getName(), isEntity, packageName, generateCreate, generateUpdate, generateRead, generateDelete);
		
		Iterator<Property> it = ModelHelper.attributes(cl);
		while (it.hasNext()) {
			Property p = it.next();
			FMProperty prop = getPropertyData(p, cl);
			fmClass.addProperty(prop);	
		}	
		
		/** @ToDo:
		 * Add import declarations etc. */		
		return fmClass;
	}
	
	private FMProperty getPropertyData(Property p, Class cl) throws AnalyzeException {
		String attName = p.getName();
		if (attName == null) 
			throw new AnalyzeException("Properties of the class: " + cl.getName() +
					" must have names!");
		Type attType = p.getType();
		if (attType == null)
			throw new AnalyzeException("Property " + cl.getName() + "." +
			p.getName() + " must have type!");
		
		String typeName = attType.getName();
		if (typeName == null)
			throw new AnalyzeException("Type ot the property " + cl.getName() + "." +
			p.getName() + " must have name!");
					
	    Stereotype stereotype = StereotypesHelper.getAppliedStereotypeByString(p, "IdProp");
	    if(stereotype != null) {
	    	List columnNameList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"name");
		    String columnName = "";
		    if (columnNameList!=null && !columnNameList.isEmpty()){
				columnName = columnNameList.get(0).toString();
			}else {
				columnName = null;
			}
	    
		    List strategyList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"strategy");
		    String strategy = "";
		    if (strategyList!=null && !strategyList.isEmpty()){
		    	EnumerationLiteral el = (EnumerationLiteral) strategyList.get(0);
		    	strategy = el.getName();
			}else {
				strategy = null;
			}
		    
			IdProp idProp = new IdProp(attType.getName(), p.getVisibility().toString(), p.getName(), columnName, strategy);
			return idProp;
	    }
	    
	    stereotype = StereotypesHelper.getAppliedStereotypeByString(p, "ColumnProp");
	    if(stereotype != null) {
	    	List columnNameList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"name");
		    String columnName = "";
		    if (columnNameList!=null && !columnNameList.isEmpty()){
				columnName = columnNameList.get(0).toString();
			}else {
				columnName = null;
			}
	    
		    List isNullableList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"isNullable");
		    boolean isNullable = true;
		    if (isNullableList!=null && !isNullableList.isEmpty()){
		    	isNullable = (boolean)isNullableList.get(0);
			}
		    
		    List isUniqueList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"isUnique");
		    boolean isUnique = true;
		    if (isUniqueList!=null && !isUniqueList.isEmpty()){
		    	isUnique = (boolean)isUniqueList.get(0);
			}
		    
		    ColumnProp columnProp = new ColumnProp(attType.getName(), p.getVisibility().toString(), p.getName(), columnName, isNullable, isUnique);
		    return columnProp;
	    }
	    
	    stereotype = StereotypesHelper.getAppliedStereotypeByString(p, "OneToMany");
	    if(stereotype != null) {
	    	List columnNameList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"name");
		    String columnName = "";
		    if (columnNameList!=null && !columnNameList.isEmpty()){
				columnName = columnNameList.get(0).toString();
			}else {
				columnName = null;
			}
		    
	        List fetchTypeList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"fetchType");
		    String fetchType = "";
		    if (fetchTypeList!=null && !fetchTypeList.isEmpty()){
		    	EnumerationLiteral el = (EnumerationLiteral) fetchTypeList.get(0);
		    	fetchType = el.getName();
			}else {
				fetchType = null;
			}
		    
	    	List cascadeTypeList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"cascadeType");
		    String cascadeType = "";
		    if (cascadeTypeList!=null && !cascadeTypeList.isEmpty()){
		    	EnumerationLiteral el = (EnumerationLiteral) cascadeTypeList.get(0);
		    	cascadeType = el.getName();
			}else {
				cascadeType = null;
			}
		    
	    	List mappedByList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"mappedBy");
		    String mappedBy = "";
		    if (mappedByList!=null && !mappedByList.isEmpty()){
		    	mappedBy = mappedByList.get(0).toString();
			}else {
				mappedBy = null;
			}
		    
		    OneToMany oneToMany = new OneToMany(attType.getName(), p.getVisibility().toString(), p.getName(), columnName, fetchType, cascadeType, mappedBy);
		    return oneToMany;
	    }
	    
	    stereotype = StereotypesHelper.getAppliedStereotypeByString(p, "ManyToOne");
	    if(stereotype != null) {
	    	List columnNameList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"name");
		    String columnName = "";
		    if (columnNameList!=null && !columnNameList.isEmpty()){
				columnName = columnNameList.get(0).toString();
			}else {
				columnName = null;
			}
		    
		    List fetchTypeList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"fetchType");
		    String fetchType = "";
		    if (fetchTypeList!=null && !fetchTypeList.isEmpty()){
		    	EnumerationLiteral el = (EnumerationLiteral) fetchTypeList.get(0);
		    	fetchType = el.getName();
			}else {
				fetchType = null;
			}
		    
	    	List cascadeTypeList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"cascadeType");
		    String cascadeType = "";
		    if (cascadeTypeList!=null && !cascadeTypeList.isEmpty()){
		    	EnumerationLiteral el = (EnumerationLiteral) cascadeTypeList.get(0);
		    	cascadeType = el.getName();
			}else {
				cascadeType = null;
			}
		    
	    	List mappedByList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"mappedBy");
		    String mappedBy = "";
		    if (mappedByList!=null && !mappedByList.isEmpty()){
		    	mappedBy = mappedByList.get(0).toString();
			}else {
				mappedBy = null;
			}
		    
		    ManyToOne manyToOne = new ManyToOne(attType.getName(), p.getVisibility().toString(), p.getName(), columnName, fetchType, cascadeType, mappedBy);
		    return manyToOne;
	    }
	    
	    stereotype = StereotypesHelper.getAppliedStereotypeByString(p, "ManyToMany");
	    if(stereotype != null) {
	    	List columnNameList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"name");
		    String columnName = "";
		    if (columnNameList!=null && !columnNameList.isEmpty()){
				columnName = columnNameList.get(0).toString();
			}else {
				columnName = null;
			}
		    
		    List fetchTypeList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"fetchType");
		    String fetchType = "";
		    if (fetchTypeList!=null && !fetchTypeList.isEmpty()){
		    	EnumerationLiteral el = (EnumerationLiteral) fetchTypeList.get(0);
		    	fetchType = el.getName();
			}else {
				fetchType = null;
			}
		    
	    	List cascadeTypeList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"cascadeType");
		    String cascadeType = "";
		    if (cascadeTypeList!=null && !cascadeTypeList.isEmpty()){
		    	EnumerationLiteral el = (EnumerationLiteral) cascadeTypeList.get(0);
		    	cascadeType = el.getName();
			}else {
				cascadeType = null;
			}
		    
	    	List joinColumnsList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"joinColumns");
		    String joinColumns = "";
		    if (joinColumnsList!=null && !joinColumnsList.isEmpty()){
		    	joinColumns = joinColumnsList.get(0).toString();
			}else {
				joinColumns = null;
			}
		    
	    	List inverseJoinColumnsList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"inverseJoinColumns");
		    String inversejoinColumns = "";
		    if (inverseJoinColumnsList!=null && !inverseJoinColumnsList.isEmpty()){
		    	inversejoinColumns = inverseJoinColumnsList.get(0).toString();
			}else {
				inversejoinColumns = null;
			}
		    
	    	List helperList = StereotypesHelper.getStereotypePropertyValue(p,stereotype,"helper");
		    String helper = "";
		    if (helperList!=null && !helperList.isEmpty()){
		    	helper = helperList.get(0).toString();
			}else {
				helper = null;
			}
		    
		    ManyToMany manyToMany = new ManyToMany(attType.getName(), p.getVisibility().toString(), p.getName(), columnName, fetchType, cascadeType, joinColumns, inversejoinColumns, helper);
		    return manyToMany;
	    } 
	    
	    return new FMProperty(typeName, p.getVisibility().toString(), attName, null);	
	    
	}	
	
	private FMEnumeration getEnumerationData(Enumeration enumeration, String packageName) throws AnalyzeException {
		FMEnumeration fmEnum = new FMEnumeration(enumeration.getName(), packageName);
		List<EnumerationLiteral> list = enumeration.getOwnedLiteral();
		for (int i = 0; i < list.size() - 1; i++) {
			EnumerationLiteral literal = list.get(i);
			if (literal.getName() == null)  
				throw new AnalyzeException("Items of the enumeration " + enumeration.getName() +
				" must have names!");
			fmEnum.addValue(literal.getName());
		}
		return fmEnum;
	}	
	
	
}
