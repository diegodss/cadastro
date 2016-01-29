<%-- Fragmento com trecho utilizado no cabecalho das paginas. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div>
	<spring:url var="banner" value="/resources/img/logotipo_bitmaker.png" />
	
	<div class="logo">
		<img src="${banner}" style="padding-top: 15px;" />
	</div>
	
	<spring:message code="app.title" var="app_title" htmlEscape="false" />
	<h1 class="title">${app_title}</h1>
</div>
