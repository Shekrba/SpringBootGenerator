package myplugin.generator.fmmodel;

public class IdProp extends FMProperty {

	private String strategy;
	
	public IdProp(String type, String visibility, String propertyName, String columnName, String strategy) {
		super(type, visibility, propertyName, columnName);
		// TODO Auto-generated constructor stub
		this.strategy = strategy;
	}
	
	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

}
