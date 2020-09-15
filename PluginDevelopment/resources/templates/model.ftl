package ${class.classPackage};

@NoArgsConstructor
@Getter
@Setter
<#if class.entity == true >
@Entity
</#if>
public class ${class.className} {  
<#list properties as property>
	  
	${property.visibility} ${property.type} ${property.propertyName};   
	
</#list>

}
