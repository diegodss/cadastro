<%-- Pagina de edicao de cadastro. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<spring:message code="label.editar" var="label_editar" htmlEscape="false" />
	<spring:message code="msg.erro.inexistente" var="erro_inexistente" htmlEscape="false" />
	
	<c:if test="${cadastro != null}" var="temcadastro">
		<c:import url="/WEB-INF/views/cadastroForm.jsp">
			<c:param name="method" value="PUT" />
			<c:param name="sublabel" value="${label_editar}" />
			<c:param name="enableRemove" value="true" />
		</c:import>
		
	   	<form:form id="deletecadastro" action="" method="DELETE"></form:form>
	   	
	   	<script>
	   	$(document).ready(function () {
	   		$("#excluir").click(function () {
	   	   		$("#deletecadastro").submit();
	     	});
	   	});
		</script>
	</c:if>
	<c:if test="${!temcadastro}">
		<h3>${erro_inexistente}</h3>
	</c:if>
	
</div>