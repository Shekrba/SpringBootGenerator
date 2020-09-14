package myplugin.generator.fmmodel;

public class ColumnProp extends FMProperty {

	private boolean isNullable;
	private boolean isUnique;
	
	public ColumnProp(String type, String visibility, String propertyName, String columnName, boolean isNullable, boolean isUnique) {
		super(type, visibility, propertyName, columnName);
		// TODO Auto-generated constructor stub
		this.isNullable = isNullable;
		this.isUnique = isUnique;
	}

	public boolean isNullable() {
		return isNullable;
	}

	public void setNullable(boolean isNullable) {
		this.isNullable = isNullable;
	}

	public boolean isUnique() {
		return isUnique;
	}

	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}
}
