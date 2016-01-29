<%-- Pagina principal da aplicacao, a listagem de importancias. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
	<spring:message code="label.importancias" var="label_importancias" htmlEscape="false" />
	<spring:message code="label.listagem" var="label_listagem" htmlEscape="false" />
	<spring:message code="label.importancia.nome" var="label_importancia_nome" htmlEscape="false" />
	<spring:message code="label.editar" var="label_editar" htmlEscape="false" />
	<spring:message code="button.atualizar" var="button_atualizar" htmlEscape="false" />
	
	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>${label_importancias} <small> ${label_listagem}</small></h3>
	</div>
	
	<table class="table table-hover">
		<thead>
			<tr>			
				<th>${label_importancia_nome}</th>
			</tr>
		</thead>
		<c:forEach items="${importancias}" var="i">
		<tr>			
			<td>
				<spring:url value="/importancia/${i.id}" var="edit_id" htmlEscape="true">
					<spring:param name="form"></spring:param>
				</spring:url>
				<a href="${edit_id}" title="${label_editar} ${i.nome}">${i.nome}</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	<form:form id="atualizaimportancia" action="synch" method="GET">
		<div class="control-group">
	   		<div class="controls">
	   			<button id="salvar" class="btn btn-success">${button_atualizar}</button>
	   		</div>
	   	</div>
	</form:form>
</div>