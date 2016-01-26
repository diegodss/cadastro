<%-- 
     Fragmento com o formulario de preenchimento com os dados da cadastro.
     Utilizado pela pagina de inclusao e edicao de cadastro.
     O formulario de cadastros utiliza o plugin Validation do JQuery, para validar os inputs.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:message code="button.salvar" var="button_salvar" htmlEscape="false" />
<spring:message code="button.cancelar" var="button_cancelar" htmlEscape="false" />
<spring:message code="button.excluir" var="button_excluir" htmlEscape="false" />

<spring:message code="label.cadastro" var="label_cadastro" htmlEscape="false" />
<spring:message code="label.cadastro.servico" var="label_cadastro_servico" htmlEscape="false" />
<spring:message code="label.cadastro.imagem" var="label_cadastro_imagem" htmlEscape="false" />
<spring:message code="label.cadastro.URL" var="label_cadastro_URL" htmlEscape="false" />
<spring:message code="label.cadastro.usuario" var="label_cadastro_usuario" htmlEscape="false" />
<spring:message code="label.cadastro.senha" var="label_cadastro_senha" htmlEscape="false" />
<spring:message code="label.cadastro.importancia" var="label_cadastro_importancia" htmlEscape="false" />

<form:form action="" method="${param.method}" modelAttribute="cadastro" class="form-horizontal" id="frmcadastro">
	<input type="hidden" name="id" value="${cadastro.id}" />
	<fieldset>
   		<legend><h3>${label_cadastro} <small> ${param.sublabel}</small></h3></legend>
   		<div class="control-group">
    		<label class="control-label">${label_cadastro_servico}</label>
    		<div class="controls">
    			<form:input path="servico" class="input-large" />
    			<form:errors path="servico" cssClass="alert alert-error" />
    		</div>
   		</div>
   		
   		<div class="control-group">
    		<label class="control-label">${label_cadastro_imagem}</label>
    		<div class="controls">
    			<form:input path="imagem" class="input-large"/>
    			<form:errors path="imagem" cssClass="alert alert-error" />
    		</div>
   		</div>
   		
   		<div class="control-group">
    		<label class="control-label">${label_cadastro_URL}</label>
    		<div class="controls">
    			<form:input path="URL" class="input-large" id="URL" />
    			<form:errors path="URL" cssClass="alert alert-error" />
    		</div>
   		</div>
   		
   		<div class="control-group">
    		<label class="control-label">${label_cadastro_senha}</label>
    		<div class="controls">
    			<form:input path="senha" class="input-large"/>
    			<form:errors path="senha" cssClass="alert alert-error" />
    		</div>
   		</div>

   		<div class="control-group">
    		<label class="control-label">${label_cadastro_usuario}</label>
    		<div class="controls">
    			<form:input path="usuario" class="input-large"/>
    			<form:errors path="usuario" cssClass="alert alert-error" />
    		</div>
   		</div>

   		<div class="control-group">
    		<label class="control-label">${label_cadastro_importancia}</label>
    		<div class="controls">
    			<form:input path="importancia" class="input-large"/>
    			<form:errors path="importancia" cssClass="alert alert-error" />
    		</div>
   		</div>
   		
   		<div class="control-group">
    		<label class="control-label">Cliente</label>
    		<div class="controls">
    			
    			 <form:select path="clienteId" items="${clientesList}" itemValue="id" itemLabel="nome" >  
    			 <!--  -->
        </form:select>
        
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
 	$("#frmcadastro").validate({
 		 	rules: {
 	 		 	servico: { required: true, minlength: 5 },
 	 		 	URL: { required: true  }
 		 	}
 	});
 	
 	$("#salvar").click(function () { $("#frmcadastro").submit(); });

});
</script>