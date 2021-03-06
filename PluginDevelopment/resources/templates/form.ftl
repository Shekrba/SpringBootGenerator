{
	"form":[
    <#assign count = 0>
    <#list properties as property>
	<#if property.class.name?ends_with(".IdProp") == false >
	<#if count!=0>,</#if>{
		"id":"${property.propertyName}",
	<#if property.type=="int" || property.type=="Integer" || property.type=="double" || property.type=="Double" || property.type=="Long" || property.type=="long">
		"type":"number",
	<#elseif property.type=="String">
		"type":"text",
	<#elseif property.type=="boolean" || property.type=="Boolean">
		"type":"checkbox",
	<#elseif property.class.name?ends_with(".ManyToOne")>
		"type":"manyToOne",
		"getOptionsUrl":"http://localhost:8088/${property.type?lower_case}",
	<#elseif property.class.name?ends_with(".ManyToMany")>
		"type":"manyToMany",
		"getOptionsUrl":"http://localhost:8088/${property.type?lower_case}",
			<#elseif property.class.name?ends_with(".OneToMany")>
		"type":"oneToMany",
		"getOptionsUrl":"http://localhost:8088/${property.type?lower_case}",
		"typesType":"${property.type?lower_case}",
	</#if>
		"name":"${property.propertyName?capitalize}",
		"row":${count},
		"col":0,
		"validation":{
			"required":true
		}
	}
	<#assign count = count + 1>
    </#if>
	</#list>
	],
	"button":{
		"name":"Submit",
		"url":"http://localhost:8088/${class.className?lower_case}"
	},
	"header":"${class.className}"
}