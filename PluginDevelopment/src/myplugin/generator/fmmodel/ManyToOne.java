package myplugin.generator.fmmodel;

public class ManyToOne extends AssociationProp {

	private String mappedBy; 
	
	public ManyToOne(String type, String visibility, String propertyName, String columnName, String fetchType,
			String cascadeType, String mappedBy) {
		super(type, visibility, propertyName, columnName, fetchType, cascadeType);
		// TODO Auto-generated constructor stub
		this.mappedBy = mappedBy;
	}

	public String getMappedBy() {
		return mappedBy;
	}

	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}

}

