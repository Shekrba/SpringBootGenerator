package myplugin.generator.fmmodel;

public class ManyToMany extends AssociationProp {

	private String joinColumns;
	private String inverseJoinColumns;
	private String helper;
	
	public ManyToMany(String type, String visibility, String propertyName, String columnName, String fetchType,
			String cascadeType, String joinColumns, String inverseJoinColumns, String helper) {
		super(type, visibility, propertyName, columnName, fetchType, cascadeType);
		// TODO Auto-generated constructor stub
		this.inverseJoinColumns = inverseJoinColumns;
		this.joinColumns = joinColumns;
		this.helper = helper;
	}

	public String getJoinColumns() {
		return joinColumns;
	}

	public void setJoinColumns(String joinColumns) {
		this.joinColumns = joinColumns;
	}

	public String getInverseJoinColumns() {
		return inverseJoinColumns;
	}

	public void setInverseJoinColumns(String inverseJoinColumns) {
		this.inverseJoinColumns = inverseJoinColumns;
	}
	
	public String getHelper() {
		return helper;
	}

	public void setHelper(String helper) {
		this.helper = helper;
	}

}
