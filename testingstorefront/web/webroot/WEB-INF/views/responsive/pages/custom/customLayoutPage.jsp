<%--
  Created by IntelliJ IDEA.
  User: Gorazd
  Date: 17. 12. 2022
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/responsive/action" %>

<template:page pageTitle="${pageTitle}">
    <cms:pageSlot position="customParagraph" var="feature" element="div" class="customBannerParagraph">
        <!-- 'position' of <<customParagraph>> in IMPEX step 4 -->
        <cms:component component="${feature}" />
    </cms:pageSlot>


    <cms:pageSlot position="testParagraph" var="feature" element="div" class="container testBannerParagraph">     <!-- testHeadingComponent -->
        <!-- 'position' of <<testParagraph>> in IMPEX step 4 -->
        <cms:component component="${feature}" />
        <cms:component component="${feature}" />
    </cms:pageSlot>


    <cms:pageSlot position="customVideo" var="feature" element="div" class="customVideoComponent">
        <!-- 'position' of <<customVideo>> in IMPEX step 4 -->
        <cms:component component="${feature}" />
    </cms:pageSlot>
</template:page>