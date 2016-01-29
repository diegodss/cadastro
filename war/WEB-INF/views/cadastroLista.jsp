<%-- Pagina principal da aplicacao, a listagem de cadastros. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
	<spring:message code="label.cadastros" var="label_cadastros" htmlEscape="false" />
	<spring:message code="label.listagem" var="label_listagem" htmlEscape="false" />
	<spring:message code="label.cadastro.imagem" var="label_cadastro_imagem" htmlEscape="false" />
	<spring:message code="label.cadastro.servico" var="label_cadastro_servico" htmlEscape="false" />
	<spring:message code="label.cadastro.URL" var="label_cadastro_URL" htmlEscape="false" />
	<spring:message code="label.cadastro.usuario" var="label_cadastro_usuario" htmlEscape="false" />
	<spring:message code="label.cadastro.senha" var="label_cadastro_senha" htmlEscape="false" />
	<spring:message code="label.editar" var="label_editar" htmlEscape="false" />
	<spring:message code="button.atualizar" var="button_atualizar" htmlEscape="false" />

	<spring:url var="copiarIco" value="/resources/img/copiar-ico-32.png" />	
		
	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>${label_cadastros} <small> ${label_listagem}</small></h3>
	</div>
	
	<table class="table table-hover">
		<thead>
			<tr>			
				<th>${label_cadastro_imagem}</th>
				<th>${label_cadastro_servico}</th>
				<th>${label_cadastro_URL}</th>
				<th>${label_cadastro_usuario}</th>
				<th>${label_cadastro_senha}</th>
			</tr>
		</thead>
		<c:forEach items="${cadastros}" var="c">
		<tr>			
			<td>
				<spring:url value="/cadastro/${c.id}" var="edit_url" htmlEscape="true">
					<spring:param name="form"></spring:param>
				</spring:url>
				<a href="${edit_url}" title="${label_editar} ${c.imagem}">
				<img src="${c.imagem}" width="24" />
				</a>
			</td>
			<td><a href="${edit_url}" title="${label_editar}">${c.servico}</a></td>
			<td><a href="${c.URL}" target="_blank">${c.URL}</a></td>
			<td>${c.usuario}</td>
			<td><a href="#${c.senha}" id="copypassword"><img src="${copiarIco}" width="24" /></a></td>
		</tr>
		</c:forEach>
	</table>
	<form:form id="atualizacadastro" action="synch" method="GET">
		<div class="control-group">
	   		<div class="controls">
	   			<button id="salvar" class="btn btn-success">${button_atualizar}</button>
	   		</div>
	   	</div>
	</form:form>
</div>



	




<script>
$(document).ready(function () {
 	$("#copypassword").click(function(){
		var text = $(this).attr('href').replace(/^.*?(#|$)/,'');
		
		var $temp = $("<input>");
		$("body").append($temp);
		$temp.val(text).select();
		document.execCommand("copy");
		$temp.remove();
		  
	});
	

	
});
</script>