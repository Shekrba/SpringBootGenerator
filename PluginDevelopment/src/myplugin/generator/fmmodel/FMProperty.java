package myplugin.generator.fmmodel;


public class FMProperty {
	//Property type
	protected String type;
	// Property visibility (public, private, protected, package)
	protected String visibility;
	
	protected String propertyName;
	
	protected String columnName;
		
	public FMProperty(String type, String visibility, String propertyName, String columnName) {
		super();
		this.type = type;
		this.visibility = visibility;
		this.propertyName = propertyName;
		this.columnName = columnName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	
	/** @ToDo: Add length, precision, unique... whatever is needed for ejb class generation
	 * Also, provide these meta-attributes or tags in the modeling languange metaclass or 
	 * stereotype */

	
	
	
	
}
