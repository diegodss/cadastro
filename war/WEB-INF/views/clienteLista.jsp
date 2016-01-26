<%-- Pagina principal da aplicacao, a listagem de clientes. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
	<spring:message code="label.clientes" var="label_clientes" htmlEscape="false" />
	<spring:message code="label.listagem" var="label_listagem" htmlEscape="false" />
	<spring:message code="label.cliente.nome" var="label_cliente_nome" htmlEscape="false" />
	<spring:message code="label.cliente.contato" var="label_cliente_contato" htmlEscape="false" />
	<spring:message code="label.cliente.rut" var="label_cliente_rut" htmlEscape="false" />
	<spring:message code="label.editar" var="label_editar" htmlEscape="false" />
	<spring:message code="button.atualizar" var="button_atualizar" htmlEscape="false" />
	
	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>${label_clientes} <small> ${label_listagem}</small></h3>
	</div>
	
	<table class="table table-hover">
		<thead>
			<tr>			
				<th>${label_cliente_nome}</th>
				<th>${label_cliente_contato}</th>
				<th>${label_cliente_rut}</th>
			</tr>
		</thead>
		<c:forEach items="${clientes}" var="c">
		<tr>			
			<td>
				<spring:url value="/cliente/${c.id}" var="edit_rut" htmlEscape="true">
					<spring:param name="form"></spring:param>
				</spring:url>
				<a href="${edit_rut}" title="${label_editar} ${c.nome}">${c.nome}</a>
			</td>
			<td>${c.contato}</td>
			<td>${c.rut}</td>
		</tr>
		</c:forEach>
	</table>
	<form:form id="atualizacliente" action="synch" method="GET">
		<div class="control-group">
	   		<div class="controls">
	   			<button id="salvar" class="btn btn-success">${button_atualizar}</button>
	   		</div>
	   	</div>
	</form:form>
</div>