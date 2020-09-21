package ${class.classPackage};

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

<#list importedPackages as package>
import ${package};
</#list>

@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer"})
@NoArgsConstructor
@Getter
@Setter
<#if class.entity == true>
@Entity
</#if>
<#if class.tableName?? && class.tableName != "">
@Table(name = "${class.tableName}")
</#if>
public class ${class.className} {  
<#list properties as property>
	
	<#if property.class.name?ends_with(".IdProp")>
	@Id
	@GeneratedValue(strategy = ${property.strategy})
	${property.visibility} ${property.type} ${property.propertyName};   
	
	<#elseif property.class.name?ends_with(".ColumnProp")>
	@Column(name = "${property.columnName}", unique = ${property.isUnique}, nullable = ${property.isNullable})
	${property.visibility} ${property.type} ${property.propertyName};   

	<#elseif property.class.name?ends_with(".OneToMany")>
	@OneToMany(mappedBy = "${property.mappedBy}"<#if property.fetchType??>, fetch = FetchType.${property.fetchType}</#if><#if property.cascadeType??>, cascade = CascadeType.${property.cascadeType}</#if>)
	${property.visibility} Set<${property.type}> ${property.propertyName} = new HashSet<${property.type}>();

	<#elseif property.class.name?ends_with(".ManyToOne")>
	@ManyToOne(mappedBy = "${property.mappedBy}"<#if property.fetchType??>, fetch = FetchType.${property.fetchType}</#if><#if property.cascadeType??>, cascade = CascadeType.${property.cascadeType}</#if>)
	${property.visibility} ${property.type} ${property.propertyName};  

	<#elseif property.class.name?ends_with(".ManyToMany")>
	@ManyToMany
    	@JoinTable(
            name = "${property.inverseJoinColumns}_${property.joinColumns}",
            joinColumns = @JoinColumn(name = "${property.joinColumns}_id"),
            inverseJoinColumns = @JoinColumn(name = "${property.inverseJoinColumns}_id")
    	)
    ${property.visibility} Set<${property.type}> ${property.propertyName} = new HashSet<>();	
    <#else>
    ${property.visibility} ${property.type} ${property.propertyName};   
	</#if>  
</#list>

	public ${class.className}() {

	}

}