<%@ page trimDirectiveWhitespaces="true" contentType="application/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<c:set var="productName" value="${fn:escapeXml(product.name)}" />

{
  "addToCartLayer":"<spring:escapeBody javaScriptEscape="true" htmlEscape="false">
      <spring:htmlEscape defaultHtmlEscape="true">
      <spring:theme code="text.addToCart" var="addToCartText"/>
      <c:url value="/cart" var="cartUrl"/>
      <ycommerce:testId code="addToCartPopup">
          <div id="addToCartLayer" class="add-to-cart">

              <ycommerce:testId code="checkoutLinkInPopup">
                  <a href="${fn:escapeXml(cartUrl)}" class="btn btn-primary btn-block add-to-cart-button" id="confirmed">
                      <spring:theme code="checkout.confirm" />
                  </a>
              </ycommerce:testId>

              <a href="" class="btn btn-default btn-block js-mini-cart-close-button">
                <spring:theme code="cart.page.cancel"/>
              </a>,

          </div>
      </ycommerce:testId>
      </spring:htmlEscape>
  </spring:escapeBody>"
}



