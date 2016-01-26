<%-- Fragmento com trecho utilizado no menu de navegacao. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
	<spring:message code="label.cadastros" var="label_cadastros" htmlEscape = "false" />
	<spring:message code="label.clientes" var="label_clientes" htmlEscape = "false" />
	<spring:message code="menu.lista" var="menu_lista" htmlEscape="false" />
	<spring:message code="menu.incluir" var="menu_incluir" htmlEscape="false" />
	<spring:message code="menu.sobre" var="menu_sobre" htmlEscape="false" />
	
	<ul class="nav nav-list" style="padding-top: 15px;">

		<li class="divider"></li>
		<li class="${active == 'sobre' ? 'active' : ''}">
			<a href="/sobre">${menu_sobre}</a>
		</li>
		<li class="divider"></li>
		<li><em>${label_cadastros}</em></li>
		
		<li class="${active == 'listaCadastro' ? 'active' : ''}">
			<a href="/cadastro">${menu_lista}</a>
		</li>
		<li class="${active == 'incluirCadastro' ? 'active' : ''}">
			<a href="/cadastro?form">${menu_incluir}</a>
        </li>
		<li class="divider"></li>
		<li><em>${label_clientes}</em></li>
		
		<li class="${active == 'listaCliente' ? 'active' : ''}">
			<a href="/cliente">${menu_lista}</a>
		</li>
		<li class="${active == 'incluirCliente' ? 'active' : ''}">
			<a href="/cliente?form">${menu_incluir}</a>
        </li>
		<li class="divider"></li>
	</ul>
</div>
