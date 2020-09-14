package myplugin.generator.fmmodel;

public class AssociationProp extends FMProperty {

	protected String fetchType;
	protected String cascadeType;
	
	public String getFetchType() {
		return fetchType;
	}

	public void setFetchType(String fetchType) {
		this.fetchType = fetchType;
	}

	public String getCascadeType() {
		return cascadeType;
	}

	public void setCascadeType(String cascadeType) {
		this.cascadeType = cascadeType;
	}

	public AssociationProp(String type, String visibility, String propertyName, String columnName, String fetchType, String cascadeType) {
		super(type, visibility, propertyName, columnName);
		// TODO Auto-generated constructor stub
		this.fetchType = fetchType;
		this.cascadeType = cascadeType;
	}
	

}
