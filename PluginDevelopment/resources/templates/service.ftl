package rs.ac.uns.ftn.mbrs.demo.testApp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.mbrs.demo.template.GService;
import rs.ac.uns.ftn.mbrs.demo.utils.ObjectMapperUtils;

import java.util.List;
import java.util.HashSet;


@Service
public class ${class.className}ServiceImpl implements GService<${class.className}, ${class.className}DTO> {

    @Autowired
    ${class.className}Repo ${class.className?lower_case}Repo;
    
    <#list properties as property>
		<#if property.class.name?ends_with(".ManyToOne") || property.class.name?ends_with(".ManyToMany")>
	
	@Autowired
    ${property.type}Repo ${property.type?lower_case}Repo;
		</#if>	
	</#list>
    
    @Override
    public List<${class.className}> getAll() {
        return ${class.className?lower_case}Repo.findAll();
    }

    @Override
    public ${class.className} save(${class.className}DTO obj) {
    	${class.className} ${class.className?lower_case} = ObjectMapperUtils.map(obj, ${class.className}.class);
    
    <#assign flag = false>
    <#list properties as property>
		<#if property.class.name?ends_with(".ManyToOne")>
		${property.type} ${property.type?lower_case} = ${property.type?lower_case}Repo.getOne(obj.get${property.propertyName?cap_first}());
		${class.className?lower_case}.set${property.propertyName?cap_first}(${property.type?lower_case});
		${property.type?lower_case}.get${property.mappedBy?cap_first}().add(${class.className?lower_case});
		${class.className?lower_case} = ${class.className?lower_case}Repo.save(${class.className?lower_case});
		${property.type?lower_case}Repo.save(${property.type?lower_case});
		<#assign flag = true>
		<#elseif property.class.name?ends_with(".ManyToMany")>
		for(Long id : obj.get${property.propertyName?cap_first}()){
			${property.type} ${property.type?lower_case} = ${property.type?lower_case}Repo.getOne(id);
			${class.className?lower_case}.get${property.propertyName?cap_first}().add(${property.type?lower_case});
			${property.type?lower_case}.get${property.helper?cap_first}().add(${class.className?lower_case});
			${class.className?lower_case} = ${class.className?lower_case}Repo.save(${class.className?lower_case});
			${property.type?lower_case}Repo.save(${property.type?lower_case});
		}
		${class.className?lower_case} = ${class.className?lower_case}Repo.save(${class.className?lower_case});
		<#assign flag = true>			
		</#if>
	</#list>
		<#if flag == false>
		${class.className?lower_case} = ${class.className?lower_case}Repo.save(${class.className?lower_case});
		</#if>
		return ${class.className?lower_case};
	}
	
	@Override
    public ${class.className} update(${class.className}DTO obj) {
    	${class.className} ${class.className?lower_case} = ObjectMapperUtils.map(obj, ${class.className}.class);

		${class.className} old${class.className} = null;
    <#assign flag = false>
    <#list properties as property>
		<#if property.class.name?ends_with(".ManyToOne")>
		${property.type} ${property.type?lower_case} = ${property.type?lower_case}Repo.getOne(obj.get${property.propertyName?cap_first}());
		${class.className?lower_case}.set${property.propertyName?cap_first}(${property.type?lower_case});
		old${class.className} = ${class.className?lower_case}Repo.getOne(obj.getId());
		old${class.className}.get${property.propertyName?cap_first}().get${property.mappedBy?cap_first}().remove(old${class.className});
		${property.type?lower_case}.get${property.mappedBy?cap_first}().add(${class.className?lower_case});
		${class.className?lower_case} = ${class.className?lower_case}Repo.save(${class.className?lower_case});
		${property.type?lower_case}Repo.save(${property.type?lower_case});
		<#assign flag = true>
		<#elseif property.class.name?ends_with(".ManyToMany")>
		<#if flag == false>
		old${class.className} = ${class.className?lower_case}Repo.getOne(obj.getId());
		</#if>
		
		old${class.className}.set${property.propertyName?cap_first}(new HashSet<>());
				
		for(Long id : obj.get${property.propertyName?cap_first}()){
			${property.type} ${property.type?lower_case} = ${property.type?lower_case}Repo.getOne(id);			
			${class.className?lower_case}.get${property.propertyName?cap_first}().add(${property.type?lower_case});
			${property.type?lower_case}.get${property.helper?cap_first}().add(${class.className?lower_case});
			${class.className?lower_case} = ${class.className?lower_case}Repo.save(${class.className?lower_case});
			${property.type?lower_case}Repo.save(${property.type?lower_case});
		}
		<#assign flag = true>			
		</#if>
	</#list>
		<#if flag == false>
		${class.className?lower_case} = ${class.className?lower_case}Repo.save(${class.className?lower_case});
		</#if>
		return ${class.className?lower_case};
	}
	
	@Override
    public ${class.className} get(Long id) {
    	return ${class.className?lower_case}Repo.getOne(id);
    }
	
	@Override
    public void delete(Long id) {
    	${class.className?lower_case}Repo.delete(${class.className?lower_case}Repo.getOne(id));
    }
}