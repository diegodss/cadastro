<%-- Fragmento com trecho utilizado no rodape das paginas. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="footer">
	<spring:message code="label.detalhes.projeto" var="label_detalhes_projeto" htmlEscape="false" />
	<spring:message code="label.bitmaker" var="label_bitmaker" htmlEscape="false" />
	<spring:message code="label.saiba.mais" var="label_saiba_mais" htmlEscape="false" />
	
	<div style="border-top: 1px solid #E5E5E5; padding-top: 10px">
    	<a href="http://www.bitmaker.com.br" title="${label_bitmaker}" target="open">${label_bitmaker}</a>
    	| <small>${label_detalhes_projeto}
    	(<a href="#" title="${label_saiba_mais}" target="open">${label_saiba_mais}</a>)
    	</small>
    </div>
</div>
