package myplugin.generator.fmmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FMClass {	
	
	private String tableName;
	
	private String className;
	
	private boolean entity;
	
	private String classPackage;
	
	//Class properties
	private List<FMProperty> FMProperties = new ArrayList<FMProperty>();
	
	//list of packages (for import declarations) 
	private List<String> importedPackages = new ArrayList<String>();
	
	//-----------CRUD----------
	private boolean generateCreate;
	
	private boolean generateUpdate;
	
	private boolean generateRead;
	
	private boolean generateDelete;
	//--------------------------
	
	/** @ToDo: add list of methods */
	
	
	public FMClass(String tableName, String className, boolean entity, String classPackage, boolean create, boolean update, boolean read, boolean delete) {
		this.tableName = tableName;
		this.className = className;
		this.entity = entity;
		this.classPackage = classPackage;
		this.generateCreate = create;
		this.generateUpdate = update;
		this.generateRead = read;
		this.generateDelete = delete;
	}	
	
	public List<FMProperty> getFMProperties() {
		return FMProperties;
	}

	public void setFMProperties(List<FMProperty> fMProperties) {
		FMProperties = fMProperties;
	}

	public boolean isGenerateCreate() {
		return generateCreate;
	}

	public void setGenerateCreate(boolean generateCreate) {
		this.generateCreate = generateCreate;
	}

	public boolean isGenerateUpdate() {
		return generateUpdate;
	}

	public void setGenerateUpdate(boolean generateUpdate) {
		this.generateUpdate = generateUpdate;
	}

	public boolean isGenerateRead() {
		return generateRead;
	}

	public void setGenerateRead(boolean generateRead) {
		this.generateRead = generateRead;
	}

	public boolean isGenerateDelete() {
		return generateDelete;
	}

	public void setGenerateDelete(boolean generateDelete) {
		this.generateDelete = generateDelete;
	}

	public void setImportedPackages(List<String> importedPackages) {
		this.importedPackages = importedPackages;
	}

	public List<FMProperty> getProperties(){
		return FMProperties;
	}
	
	public Iterator<FMProperty> getPropertyIterator(){
		return FMProperties.iterator();
	}
	
	public void addProperty(FMProperty property){
		FMProperties.add(property);		
	}
	
	public int getPropertyCount(){
		return FMProperties.size();
	}
	
	public List<String> getImportedPackages(){
		return importedPackages;
	}

	public Iterator<String> getImportedIterator(){
		return importedPackages.iterator();
	}
	
	public void addImportedPackage(String importedPackage){
		importedPackages.add(importedPackage);		
	}
	
	public int getImportedCount(){
		return FMProperties.size();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public boolean isEntity() {
		return entity;
	}

	public void setEntity(boolean entity) {
		this.entity = entity;
	}

	public String getClassPackage() {
		return classPackage;
	}

	public void setClassPackage(String classPackage) {
		this.classPackage = classPackage;
	}
	
	

	
	
}
