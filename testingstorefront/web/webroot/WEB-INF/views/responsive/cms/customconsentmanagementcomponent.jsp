<%--
  Created by IntelliJ IDEA.
  User: Gorazd
  Date: 7. 04. 2023
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="label" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

    <%--  COOKIES CONSENT  --%>
    <div class="register-section">
        <form:form action="consent/giveConsent" commandName="consentForm" method="post">
            <c:if test="${not empty cookiesConsentTemplateData}">
                <form:hidden path="consentTemplateId" value="${cookiesConsentTemplateData.id}" />
                <form:hidden path="consentTemplateVersion" value="${cookiesConsentTemplateData.version}" />
                <div class="checkbox">
                    <p>CONSENT_TEMPLATE_ID: ${cookiesConsentTemplateData.id}</p>
                    <label class="control-label uncased">
                        <form:checkbox path="consentGiven" disabled="false"/>
                        <c:out value="${cookiesConsentTemplateData.description}" />

                    </label>
                </div>
            </c:if>
            <%--  SUBMIT  --%>
            <input id="cookieConsent" type="submit" class="btn btn-default btn-consent" value="GIVE COOKIES CONSENT" />
        </form:form>

        <form:form action="consent/withdrawConsent" modelAttribute="consentForm" method="post">
            <form:hidden path="consentTemplateId" value="${cookiesConsentTemplateData.id}" />
            <form:hidden path="consentTemplateVersion" value="${cookiesConsentTemplateData.version}" />
            <input type="submit" class="btn btn-default btn-consent btn-consent-remove" value="REMOVE" />
        </form:form>
        <hr />
    </div>

    <%--  MARKETING CONSENT  --%>
    <div class="register-section">
        <form:form action="consent/giveConsent" commandName="consentForm" method="post">
            <c:if test="${not empty consentTemplateData}">
                <form:hidden path="consentTemplateId" value="${consentTemplateData.id}" />
                <form:hidden path="consentTemplateVersion" value="${consentTemplateData.version}" />
                <div class="checkbox">
                    <p>CONSENT_TEMPLATE_ID: ${consentTemplateData.id}</p>
                    <label class="control-label uncased">
                        <form:checkbox path="consentGiven" disabled="false"/>
                        <c:out value="${consentTemplateData.description}" />

                    </label>
                </div>
            </c:if>
            <%--  SUBMIT  --%>
            <input type="submit" class="btn btn-default btn-consent" value="GIVE MARKETING CONSENT" />
        </form:form>

        <form:form action="consent/withdrawConsent" commandName="consentForm" method="post">
            <form:hidden path="consentTemplateId" value="${consentTemplateData.id}" />
            <form:hidden path="consentTemplateVersion" value="${consentTemplateData.version}" />
            <input type="submit" class="btn btn-default btn-consent btn-consent-remove" value="REMOVE" />
        </form:form>
        <hr />
    </div>

    <%--  PROFILE CONSENT  --%>
    <div class="register-section">
        <form:form action="consent/giveConsent" commandName="consentForm" method="post">
            <c:if test="${not empty customProfileConsentTemplateData}">
                <form:hidden path="consentTemplateId" value="${customProfileConsentTemplateData.id}" />
                <form:hidden path="consentTemplateVersion" value="${customProfileConsentTemplateData.version}" />
                <div class="checkbox">
                    <p>CONSENT_TEMPLATE_ID: ${customProfileConsentTemplateData.id}</p>
                    <label class="control-label uncased">
                        <form:checkbox path="consentGiven" disabled="false"/>
                        <c:out value="${customProfileConsentTemplateData.description}" />

                    </label>
                </div>
            </c:if>
            <%--  SUBMIT  --%>
            <input type="submit" class="btn btn-default btn-consent" value="GIVE PROFILE CONSENT" />
        </form:form>

        <form:form action="consent/withdrawConsent" commandName="consentForm" method="post">
            <form:hidden path="consentTemplateId" value="${customProfileConsentTemplateData.id}" />
            <form:hidden path="consentTemplateVersion" value="${customProfileConsentTemplateData.version}" />
            <input type="submit" class="btn btn-default btn-consent btn-consent-remove" value="REMOVE" />
        </form:form>
        <hr />
    </div>

    <%--  TEST CONSENT  --%>
    <div class="register-section">
        <form:form action="consent/giveConsent" commandName="consentForm" method="post">
            <c:if test="${not empty customTestConsentTemplateData}">
                <form:hidden path="consentTemplateId" value="${customTestConsentTemplateData.id}" />
                <form:hidden path="consentTemplateVersion" value="${customTestConsentTemplateData.version}" />
                <div class="checkbox">
                    <p>CONSENT_TEMPLATE_ID: ${customTestConsentTemplateData.id}</p>
                    <label class="control-label uncased">
                        <form:checkbox path="consentGiven" disabled="false"/>
                        <c:out value="${customTestConsentTemplateData.description}" />

                    </label>
                </div>
            </c:if>
            <%--  SUBMIT  --%>
            <input type="submit" class="btn btn-default btn-consent" value="GIVE TEST CONSENT" />
        </form:form>
        <hr />
    </div>

    <%--  REDIRECT TO CONSENT MANAGEMENT  --%>
    <div class="register-section">
        <h5>NAVIGATE TO CONSENT MANAGER</h5>
        <form:form action="consent/account-management" method="post">
            <input type="submit" class="btn btn-primary btn-consent" value="CONSENT MANAGER" />
        </form:form>
        <hr />
    </div>
</body>
</html>

<style>
    .btn-consent {
        min-width: 28rem;
    }

    .btn-consent-remove {
        margin-top: 1rem;
    }
</style>