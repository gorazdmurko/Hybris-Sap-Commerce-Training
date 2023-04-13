<%@ attribute name="element" required="true" type="java.lang.String"%>
<%@ attribute name="styleClass" required="false" type="java.lang.String"%>
<%@ attribute name="parentComponent" required="false" type="de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<c:forEach items="${actions}" var="action" varStatus="idx">
	<c:if test="${action.visible}">
		<c:choose>
			<c:when test="${fn:contains(action.typeCode, 'NewCustomTrainingAction')}">
				<${element} class="${parentComponent.uid}-${action.uid}" data-index="${idx.index + 1}" class="${styleClass}">
					<cms:component component="${action}" parentComponent="${parentComponent}" evaluateRestriction="false" />
				</${element}>
			</c:when>
			<c:otherwise>
				<${ycommerce:sanitizeHtmlTagName(element)} class="${fn:escapeXml(parentComponent.uid)}-${fn:escapeXml(action.uid)}" data-index="${idx.index + 1}" class="${styleClass}">
					<cms:component component="${action}" parentComponent="${parentComponent}" evaluateRestriction="false" />
				</${ycommerce:sanitizeHtmlTagName(element)}>
			</c:otherwise>
		</c:choose>
	</c:if>
</c:forEach>

<%--	<${element} class="${parentComponent.uid}-${action.uid}" data-index="${idx.index + 1}" class="${styleClass}">--%>
<%--		<p>Element: ${ycommerce:sanitizeHtmlTagName(element)}</p>--%>
<%--		<p>Class: ${fn:escapeXml(parentComponent.uid)}-${fn:escapeXml(action.uid)}</p>--%>
<%--		<p>Data-index: ${idx.index + 1}</p>--%>
<%--		<p>Style class: ${styleClass}</p>--%>
<%--		<p>Action: ${action}</p>--%>
<%--		<p>Component: ${component}</p>--%>
<%--		<p>Parent component: ${parentComponent}</p>--%>
<%--	</${element}>--%>