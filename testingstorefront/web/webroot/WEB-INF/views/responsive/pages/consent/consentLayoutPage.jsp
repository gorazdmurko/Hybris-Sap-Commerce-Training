<%--
  Created by IntelliJ IDEA.
  User: Gorazd
  Date: 7. 04. 2023
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>

<template:page pageTitle="${pageTitle}">

    <cms:pageSlot position="customConsent" var="feature" element="div" class="container">                   <!-- feature => CustomConsentManagementComponentModel -->
        <!-- 'position' of <<customConsent>> in IMPEX step 4 -->
        <cms:component component="${feature}" />
        <h2 style="color: blue;">Component: ${feature}</h2>
    </cms:pageSlot>

</template:page>
