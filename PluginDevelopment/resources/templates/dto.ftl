package ${class.classPackage};

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

<#list importedPackages as package>
import ${package};
</#list>

@NoArgsConstructor
@Getter
@Setter
public class ${class.className}DTO {  
<#list properties as property>
	
	<#if property.class.name?ends_with(".IdProp")>
	${property.visibility} Long ${property.propertyName}Id;   
	
	<#elseif property.class.name?ends_with(".ColumnProp")>
	${property.visibility} ${property.type} ${property.propertyName};   

	<#elseif property.class.name?ends_with(".OneToMany")>
	${property.visibility} Set<Long> ${property.propertyName}Set = new HashSet<Long>();

	<#elseif property.class.name?ends_with(".ManyToOne")>
	${property.visibility} Long ${property.propertyName}Id;  

	<#elseif property.class.name?ends_with(".ManyToMany")>
    ${property.visibility} Set<Long> ${property.propertyName}Set = new HashSet<Long>();	
    <#else>
    ${property.visibility} ${property.type} ${property.propertyName};   
	</#if>  
</#list>

	public ${class.className}DTO() {

	}

}