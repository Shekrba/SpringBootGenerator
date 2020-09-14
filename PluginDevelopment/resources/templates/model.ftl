package ${class.classPackage};

public class ${class.className} {  
<#list properties as property>
	  
	${property.visibility} ${property.type} ${property.propertyName};   
	
</#list>

}
