<div class="sidebar-menu">
	<ul>
		<li class="header-menu">
			<span>Entities:</span>
		</li>
		<#list classes as class>
		<li>
			<a href="http://localhost:4200/entities/${class.className?lower_case}">
				<span>${class.className}</span>
			</a>
		</li>
		</#list>
	</ul>
</div>