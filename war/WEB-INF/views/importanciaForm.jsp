<%-- 
     Fragmento com o formulario de preenchimento com os dados da importancia.
     Utilizado pela pagina de inclusao e edicao de importancia.
     O formulario de importancias utiliza o plugin Validation do JQuery, para validar os inputs.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:message code="button.salvar" var="button_salvar" htmlEscape="false" />
<spring:message code="button.cancelar" var="button_cancelar" htmlEscape="false" />
<spring:message code="button.excluir" var="button_excluir" htmlEscape="false" />

<spring:message code="label.importancia" var="label_importancia" htmlEscape="false" />
<spring:message code="label.importancia.nome" var="label_importancia_nome" htmlEscape="false" />

<form:form action="" method="${param.method}" modelAttribute="importancia" class="form-horizontal" id="frmimportancia">
	<input type="hidden" name="id" value="${importancia.id}" />
	<fieldset>
   		<legend><h3>${label_importancia} <small> ${param.sublabel}</small></h3></legend>
   		<div class="control-group">
    		<label class="control-label">${label_importancia_nome}</label>
    		<div class="controls">
    			<form:input path="nome" class="input-large" />
    			<form:errors path="nome" cssClass="alert alert-error" />
    		</div>
   		</div>   	
   		   	
   	</fieldset>
</form:form>


<div class="control-group form-horizontal">
	<div class="controls">
		<button id="salvar" class="btn btn-success">${button_salvar}</button>
		<a href="/"><button class="btn">${button_cancelar}</button></a>
		<c:if test="${not empty param.enableRemove}">
			<button id="excluir" class="btn btn-danger">${button_excluir}</button>
		</c:if>
	</div>
</div>

<script>
$(document).ready(function () {
 	$("#frmimportancia").validate({
 		 	rules: { 	 	
 	 		 	nome: { required: true  }
 		 	}
 	});
 	
 	$("#salvar").click(function () { $("#frmimportancia").submit(); });

});
</script>