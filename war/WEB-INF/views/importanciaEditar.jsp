<%-- Pagina de edicao de importancia. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<spring:message code="label.editar" var="label_editar" htmlEscape="false" />
	<spring:message code="msg.erro.inexistente" var="erro_inexistente" htmlEscape="false" />
	
	<c:if test="${importancia != null}" var="temimportancia">
		<c:import url="/WEB-INF/views/importanciaForm.jsp">
			<c:param name="method" value="PUT" />
			<c:param name="sublabel" value="${label_editar}" />
			<c:param name="enableRemove" value="true" />
		</c:import>
		
	   	<form:form id="deleteimportancia" action="" method="DELETE"></form:form>
	   	
	   	<script>
	   	$(document).ready(function () {
	   		$("#excluir").click(function () {
	   	   		$("#deleteimportancia").submit();
	     	});
	   	});
		</script>
	</c:if>
	<c:if test="${!temimportancia}">
		<h3>${erro_inexistente}</h3>
	</c:if>
	
</div>