<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:if test="${not product.multidimensional }">
    <c:url value="/cart/add" var="addToCartUrl"/>
	<spring:url value="${product.url}/configuratorPage/{/configuratorType}" var="configureProductUrl" htmlEscape="false">
		<spring:param name="configuratorType" value="${configuratorType}" />
	</spring:url>

    <!-- set variable addToCartForm -->
    <c:set var="addToCartForm" value="addToCartForm${fn:escapeXml(product.code)}" />

	<form:form id="addToCartForm${fn:escapeXml(product.code)}" action="${addToCartUrl}" method="post" class="add_to_cart_form">
        <ycommerce:testId code="addToCartButton">
            <input type="hidden" name="productCodePost" value="${fn:escapeXml(product.code)}"/>
            <input type="hidden" name="productNamePost" value="${fn:escapeXml(product.name)}"/>
            <input type="hidden" name="productPostPrice" value="${fn:escapeXml(product.price.value)}"/>

            <c:choose>
                <c:when test="${product.stock.stockLevelStatus.code eq 'outOfStock' }">
                    <button type="button" class="btn btn-primary btn-block glyphicon glyphicon-shopping-cart"
                            aria-disabled="true" disabled="disabled">
                    </button>
                </c:when>
                <c:otherwise>
                    <!-- set variable 'addToCartButton' for id -->
                    <c:set var="addToCartButton" value="addItemToCartId${fn:escapeXml(product.code)}" />
                    <button id="${addToCartButton}" type="submit" class="btn btn-primary btn-block glyphicon glyphicon-shopping-cart js-enable-btn"
                            disabled="disabled">
                        <%-- goes to AddToCartController --%>
                    </button>
                </c:otherwise>
            </c:choose>
        </ycommerce:testId>
    </form:form>

    <%-- directly to cart - CartPageController --%>
    <div style="margin-top: 1em">
        <c:url value="/cart" var="cartUrl"/>
        <form:form id="sendMail" action="${fn:escapeXml(cartUrl)}/send/mail" method="post">
            <ycommerce:testId code="addToCartButton">
                <input type="hidden" name="productCodePost" value="${fn:escapeXml(product.code)}"/>
                <input type="hidden" name="productNamePost" value="${fn:escapeXml(product.name)}"/>
                <input type="hidden" name="productPostPrice" value="${fn:escapeXml(product.price.value)}"/>

                <c:set var="sendMailButton" />
                <button id="${sendMailButton}" type="submit" class="btn btn-primary btn-block glyphicon glyphicon-shopping-cart js-enable-btn">
                    <spring:theme text="Training Mail" />
                </button>
            </ycommerce:testId>
        </form:form>
    </div>

    <!-- if item is CONFIGURABLE -->
    <form:form id="configureForm${fn:escapeXml(product.code)}" action="${configureProductUrl}" method="get" class="configure_form">
        <c:if test="${product.configurable}">
            <c:choose>
                <c:when test="${product.stock.stockLevelStatus.code eq 'outOfStock' }">
                    <button id="configureProduct" type="button" class="btn btn-primary btn-block"
                            disabled="disabled">
                        <spring:theme code="basket.configure.product"/>
                    </button>
                </c:when>
                <c:otherwise>
                    <button id="configureProduct" type="button" class="btn btn-primary btn-block js-enable-btn" disabled="disabled"
                            onclick="location.href='${fn:escapeXml(configureProductUrl)}'">
                        <spring:theme code="basket.configure.product"/>
                    </button>
                </c:otherwise>
            </c:choose>
        </c:if>
    </form:form>
</c:if>

<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ S T A R T ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
<!-- in javascript.tag load jQuery scripts (not here) -->
<script src="https://code.jquery.com/jquery-2.2.4.js" integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" crossorigin="anonymous"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<%--<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>--%>

<!-- DIALOG -->
<div id="dialog" style="display: none" align = "center">
    <form:form method="post" action="${addToCartUrl}">
        <input type="button" value="Confirm" id="confirmId" style="margin: 1.5vw; background-color: lightseagreen; border-radius: 0.5vw;" />
        <input type="button" value="Cancel" id="cancelId" style="margin: 1.5vw; background-color: darkorange; border-radius: 0.5vw;" />
    </form:form>
</div>

<!-- script begins here -->
<script>
    $(document).ready(function() {
        <!-- 1. DIALOG -->
        $("#dialog").dialog({
            modal: true,
            autoOpen: false,
            title: "Add to Cart?",
            width: 300,
            height: 150
        });
        $("#${addToCartButton}").click(function (event) {
            event.preventDefault();
            $('#dialog').dialog('open');
            $('#confirmId').click(() => {
                console.log("Add to cart!");
                $('#dialog').dialog('close');
                $("#${addToCartForm}").submit();
                <%--$("#${addToCartForm}").attr("method", "post").attr("action", "${addToCartUrl}").submit();--%>
            });
            $("#cancelId").click(() => {
                console.log("Cancel clicked!");
                $('#dialog').dialog('close');
            })
        });
    })
</script>
<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ E N D ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->