<%-- 
     Fragmento com o formulario de preenchimento com os dados da cliente.
     Utilizado pela pagina de inclusao e edicao de cliente.
     O formulario de clientes utiliza o plugin Validation do JQuery, para validar os inputs.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:message code="button.salvar" var="button_salvar" htmlEscape="false" />
<spring:message code="button.cancelar" var="button_cancelar" htmlEscape="false" />
<spring:message code="button.excluir" var="button_excluir" htmlEscape="false" />

<spring:message code="label.cliente" var="label_cliente" htmlEscape="false" />
<spring:message code="label.cliente.contato" var="label_cliente_contato" htmlEscape="false" />
<spring:message code="label.cliente.nome" var="label_cliente_nome" htmlEscape="false" />
<spring:message code="label.cliente.rut" var="label_cliente_rut" htmlEscape="false" />

<form:form action="" method="${param.method}" modelAttribute="cliente" class="form-horizontal" id="frmcliente">
	<input type="hidden" name="id" value="${cliente.id}" />
	<fieldset>
   		<legend><h3>${label_cliente} <small> ${param.sublabel}</small></h3></legend>
   		<div class="control-group">
    		<label class="control-label">${label_cliente_nome}</label>
    		<div class="controls">
    			<form:input path="nome" class="input-large" />
    			<form:errors path="nome" cssClass="alert alert-error" />
    		</div>
   		</div>   	
   		
   		<div class="control-group">
    		<label class="control-label">${label_cliente_rut}</label>
    		<div class="controls">
    			<form:input path="rut" class="input-large" id="rut" />
    			<form:errors path="rut" cssClass="alert alert-error" />
    		</div>
   		</div>
   		
   		<div class="control-group">
    		<label class="control-label">${label_cliente_contato}</label>
    		<div class="controls">
    			<form:input path="contato" class="input-large"/>
    			<form:errors path="contato" cssClass="alert alert-error" />
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
 	$("#frmcliente").validate({
 		 	rules: {
 	 		 	contato: { required: true, minlength: 5 },
 	 		 	nome: { required: true  }
 		 	}
 	});
 	
 	$("#salvar").click(function () { $("#frmcliente").submit(); });

});
</script>