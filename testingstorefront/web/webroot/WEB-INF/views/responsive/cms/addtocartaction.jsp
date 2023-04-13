<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:url value="${url}" var="addToCartUrl"/>
<spring:url value="${product.url}/configuratorPage/{/configuratorType}" var="configureProductUrl" htmlEscape="false">
    <spring:param name="configuratorType"  value="${configuratorType}"/>
</spring:url>

<product:addToCartTitle/>

<!-- CONFIGURE -->
<form:form method="post" id="configureForm" class="configure_form" action="${configureProductUrl}">
	<c:if test="${product.purchasable}">
		<input type="hidden" maxlength="3" size="1" id="qty" name="qty" class="qty js-qty-selector-input" value="1">
	</c:if>
	<input type="hidden" name="productCodePost" value="${fn:escapeXml(product.code)}"/>

	<c:if test="${empty showAddToCart ? true : showAddToCart}">
		<c:set var="buttonType">button</c:set>
		<c:if test="${product.purchasable and product.stock.stockLevelStatus.code ne 'outOfStock' }">
			<c:set var="buttonType">submit</c:set>
		</c:if>
		<c:choose>
			<c:when test="${fn:contains(buttonType, 'button')}">
				<c:if test="${product.configurable}">
					<button id="configureProduct" type="button" class="btn btn-primary btn-block js-enable-btn outOfStock" disabled="disabled">
						<spring:theme code="basket.configure.product"/>
					</button>
				</c:if>
			</c:when>
			<c:otherwise>
				<c:if test="${product.configurable}">
					<button id="configureProduct" type="${buttonType}" class="btn btn-primary btn-block js-enable-btn" disabled="disabled"
							name="configure">
						<spring:theme code="basket.configure.product"/>
					</button>
				</c:if>
			</c:otherwise>
		</c:choose>
	</c:if>
</form:form>

<!-- ADD TO CART -->
<form:form method="post" id="addToCartForm" class="add_to_cart_form" action="${addToCartUrl}">
	<c:if test="${product.purchasable}">
		<input type="hidden" maxlength="3" size="1" id="qty" name="qty" class="qty js-qty-selector-input" value="1">
	</c:if>
	<input type="hidden" name="productCodePost" value="${fn:escapeXml(product.code)}"/>

	<c:if test="${empty showAddToCart ? true : showAddToCart}">		<!--  showAddToCart == empty ? true : showAddToCart -->
		<c:set var="buttonType">button</c:set>
		<c:if test="${product.purchasable and product.stock.stockLevelStatus.code ne 'outOfStock' }">
			<c:set var="buttonType">submit</c:set>
		</c:if>
		<c:choose>
			<c:when test="${fn:contains(buttonType, 'button')}">
				<button type="${buttonType}" class="btn btn-primary btn-block js-add-to-cart btn-icon glyphicon-shopping-cart outOfStock" disabled="disabled">
					<spring:theme code="product.variants.out.of.stock"/>
				</button>
			</c:when>
			<c:otherwise>
				<%-- ycommerce:testId --%>
				<%--/yacceleratorstorefront/web/webroot/WEB-INF/common/tld/ycommercetags.tld--%>
				<%--/yacceleratorstorefront/web/src/de/hybris/platform/yacceleratorstorefront/tags/TestIdTag.java--%>
				<ycommerce:testId code="addToCartButton">
					<!-- change the flow here & bellow !!!!!!! -->
					<button id="addToCartButton" type="${buttonType}" class="btn btn-primary btn-block js-add-to-cart js-enable-btn btn-icon glyphicon-shopping-cart" disabled="disabled">
						<spring:theme code="basket.add.to.basket"/>
					</button>
				</ycommerce:testId>
			</c:otherwise>
		</c:choose>
	</c:if>
</form:form>



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

<!-- SCRIPT -->
<script>
	$(document).ready(function() {
		// BROWSERs POPUP
		$("#addToCartButton").click((event) => {
			event.preventDefault();
			if (confirm("Want to continue?")) {
				console.log("Test confirm!");
				$("#addToCartForm").submit();
				<%--$("#addToCartForm").attr("method", "post").attr("action", "${addToCartUrl}").submit();--%>
			} else {
				console.log("Test cancel!");
			}
		});

		<!-- 1. DIALOG -->
		<%--$("#dialog").dialog({--%>
		<%--	modal: true,--%>
		<%--	autoOpen: false,--%>
		<%--	title: "Add to Cart?",--%>
		<%--	width: 300,--%>
		<%--	height: 150--%>
		<%--});--%>
		<%--$("#${addToCartButton}").click(function (event) {--%>
		<%--	event.preventDefault();--%>
		<%--	$('#dialog').dialog('open');--%>
		<%--	$('#confirmId').click(() => {--%>
		<%--		console.log("Add to cart!");--%>
		<%--		$('#dialog').dialog('close');--%>
		<%--		$("#${addToCartForm}").submit();--%>
		<%--		&lt;%&ndash;$("#${addToCartForm}").attr("method", "post").attr("action", "${addToCartUrl}").submit();&ndash;%&gt;--%>
		<%--	});--%>
		<%--	$("#cancelId").click(() => {--%>
		<%--		console.log("Cancel clicked!");--%>
		<%--		$('#dialog').dialog('close');--%>
		<%--	})--%>
		<%--});--%>
		//  catch MODAL POPUP
		// $("#addToCartButton").click(function () {
		//     // $('#modalPopup').modal('show');
		//     $('#modalPopup').modal('show');
		//     $("#save").click(() => {
		//         $('#modalPopup').modal('hide');
		//     });
		//     $("#close").click(() => {
		//         $('#modalPopup').modal('hide');
		//         // $("#getUsers").attr('method', 'post').attr('action', 'registerUser').submit();
		//     })
		// });
	})
</script>
<!-- MODAL -->
<div class="modal fade" id="modalPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Modal Popup</h4>
			</div>
			<div class="modal-body">Welcome to Modal Popup</div>
			<div class="modal-footer">
				<%--                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
				<%--                    <button type="button" class="btn btn-primary" id="save">Save</button>--%>
				<form id="getUsers" action="getUsers">
					<input type="button" class="btn btn-default" data-dismiss="modal" value="Close" id="close" />
					<input type="submit" value="SAVE" id="save">
				</form>
			</div>
		</div>
	</div>
</div>