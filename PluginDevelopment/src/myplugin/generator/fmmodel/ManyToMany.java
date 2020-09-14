package myplugin.generator.fmmodel;

public class ManyToMany extends AssociationProp {

	private String joinColumns;
	private String inverseJoinColumns;
	
	public ManyToMany(String type, String visibility, String propertyName, String columnName, String fetchType,
			String cascadeType, String joinColumns, String inverseJoinColumns) {
		super(type, visibility, propertyName, columnName, fetchType, cascadeType);
		// TODO Auto-generated constructor stub
		this.inverseJoinColumns = inverseJoinColumns;
		this.joinColumns = joinColumns;
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

}
