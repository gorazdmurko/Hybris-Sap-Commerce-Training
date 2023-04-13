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
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>

<template:page pageTitle="${pageTitle}">
    <cms:pageSlot position="customParagraph" var="feature" element="div" class=" container customBannerParagraph">                 <!-- feature => CustomParagraphComponentModel -->
        <!-- 'position' of <<customParagraph>> in IMPEX step 4 -->
        <h2 style="color: blue;">Component: ${feature}</h2>
        <cms:component component="${feature}" />
    </cms:pageSlot>

    <!-- IMPEX -- testHeadingComponent -->
    <cms:pageSlot position="testParagraph" var="feature" element="div" class="container testBannerParagraph">           <!-- feature => CMSParagraphComponentModel -->
        <!-- 'position' of <<testParagraph>> in IMPEX step 4 -->
        <h2 style="color: blue;">Component: ${feature}</h2>
        <cms:component component="${feature}" />
        <cms:component component="${feature}" />
        <product:addToCartTitle />
    </cms:pageSlot>

    <h1>VIDEO START</h1>
    <cms:pageSlot position="customVideo" var="feature" element="div" class="container customVideoComponent">                      <!-- feature => TrainingVideoComponentModel -->
        <!-- 'position' of <<customVideo>> in IMPEX step 4 -->
        <h2 style="color: blue;">Component: ${feature}</h2>
        <cms:component component="${feature}" />
    </cms:pageSlot>
    <h1>VIDEO END</h1>
</template:page>