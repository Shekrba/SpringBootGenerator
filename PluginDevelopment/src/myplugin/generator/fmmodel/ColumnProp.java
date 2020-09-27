package myplugin.generator.fmmodel;

public class ColumnProp extends FMProperty {

	private boolean nullable;
	private boolean unique;
	
	public ColumnProp(String type, String visibility, String propertyName, String columnName, boolean isNullable, boolean isUnique) {
		super(type, visibility, propertyName, columnName);
		// TODO Auto-generated constructor stub
		this.nullable = isNullable;
		this.unique = isUnique;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean isNullable) {
		this.nullable = isNullable;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean isUnique) {
		this.unique = isUnique;
	}
}
