<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<%-- load jQuery scripts in javascript.tag file (not here) --%>
<script src="https://code.jquery.com/jquery-2.2.4.js" integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" crossorigin="anonymous"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script>
    <%-- upload javascript file --%>
    <%@ include file="/static-resources/accountProfileEdit.js" %>
</script>

<%--<script>--%>
<%--    function submitForm() {--%>
<%--        $("#fileUploadForm").submit();--%>
<%--    }--%>
<%--</script>--%>


<!-- FILE UPLOAD FORM  (test 1, 2) -->
<div class="item_container">
    <div class="col-md-8">
        <form:form name="formFileUpload" action="${contextPath}/my-account/upload-file/upload" method="post" cssClass="box"
                   commandName="uploadFile"
                   enctype="multipart/form-data">
            <div class="wizard__background registration__container">
                <div class="row">
                    <div class="col-md-12">
                        <h3>
                            Upload 1
                            <span class="font-normal">(optional)</span></h3>
                    </div>
                </div>
                <div class="row ">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-8">
                                <p>
                                    Description
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-8">
                                <div class="drag-and-drop-upload box__input upload__container">
                                    <input type="file" name="file[]" id="file" class="box__file" style="display:block" />
                                    <i class="fa fa-file-o" aria-hidden="true"></i>
                                    <p>Please work you little fucker</p>
                                    <label class="atag__upload-link" for="file">Select from desktop</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4 col-md-offset-8">
                        <button type="submit" class="button button--info button--full pull-right">
                            <p>Continue</p>
                        </button>
                    </div>
                </div>
            </div>
        </form:form>
    </div>

    <hr />

    <!-- FILE UPLOAD FORM 3 - with helper class -->
    <div class="form">
        <h2>Upload Form Class 2</h2>
        <fieldset>
            <c:set var="parseXmlFromFile" value="${contextPath}/my-account/parseXmlFromFile" />
            <%-- 1. FORM --%>
            <form:form id="fileUploadForm" name="fileUploadFormContainer" action="${parseXmlFromFile}"
                       method="post" modelAttribute="fileUploadFormContainer" enctype="multipart/form-data">
<%--                <input type="hidden" path="originalParameters" name="originalParameters" />--%>
                <table>
                    <tr>
                        <td><label for="name">Name</label></td>
                        <td><input name="name" id="name" type="text" /></td>
                    </tr>
                    <tr>
                        <td><label for="age">Age</label></td>
                        <td><input name="age" id="age" type="number" /></td>
                    </tr>
                    <tr>
                        <td><label  for="multi">Multipart</label>></td>
                        <td><input name="multipartFile" type="file" id="multi" class="box__file" /></td>
                    </tr>
                    <tr>
                        <td><label  for="media">MediaType</label>></td>
                        <td><input name="mediaTypeFile" type="file" id="media" class="box__file" /></td>
                    </tr>
                </table>
                <input type="button" onclick="submitForm()" value="SAVE TO CLASS" />
            </form:form>
        </fieldset>
    </div>

    <hr />

    <%-- FILE UPLOAD FORM 4 --%>
    <div class="form">
        <h2>Upload w/HTML form</h2>
        <%--https://electronics.local:9002/testingstorefront/my-account/parseXmlFile--%>
        <c:set var="parseXmlFile" value="${contextPath}/my-account/parseXmlFile" />
        <form:form action="${parseXmlFile}" method="post" enctype="multipart/form-data">
            <label for="selectedFile"><h3>File to upload:</h3></label>
            <input type="file" name="files[]" id="selectedFile" />
            <input type="submit" value="Upload File" />
        </form:form>
    </div>

    <hr />

    <%-- 3. FORM ~~ using ajax & jQuery --%>
    <div class="form">
        <h2>Upload w/jQuery</h2>
        <form:form id="loadFileId">
            <label for="selectedFile"><h3>File to upload:</h3></label>
            <input type="file" value="LOAD" id="loadFile" />
        </form:form>
        <pre id="file-content" hidden="hidden" style="margin: 1vw;"></pre>
        <pre id="file-response" hidden="hidden" style="margin: 1vw;"></pre>
    </div>
</div>


<%-- POPUP button --%>
<div style="margin: 1vw;">
    <form:form id="formId">
        <input type="button" value="POPUP" id="formSubmit" />
    </form:form>
</div>

<%--  POPUP MODAL dialog --%>
<div>
    <div style="text-align:center; margin-top:10%">
        <button id="btnShow" class="btn btn-success">Show Modal Popup</button>
    </div>
    <div class="modal fade" id="SampleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Modal Popup</h4>
                </div>
                <div class="modal-body">Welcome to Modal Popup</div>
                <div class="modal-footer">
                    <form id="getUsers" action="">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Close" id="close" />
                        <input type="submit" value="SAVE" id="save">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<%-- ACCOUNT TOKENS --%>
<div>
    <div class="account-section-header">
        <div class="row">
            <div class="container-lg col-md-6">
                <spring:theme code="text.account.profile.updatePersonalDetails"/>
            </div>
        </div>
    </div>
    <div>
        <h2>CUSTOMER</h2>
        <br />
        <h3>EXTERNAL TOKENS</h3>
        <table border="1">
            <tr>
                <th>AbcEnum</th>
                <th>Value</th>
            </tr>
            <
            <c:forEach var="item" items="${customer.externalTokens}">   <!-- 'customer' declared in AccountPageController -->
                <tr style="color: #0e90d2">
                    <td><c:out value="${item.abc}" /></td>
                    <td><c:out value="${item.value}" /></td>
                </tr>
            </c:forEach>
        </table>
        <br />
        <ul style="margin-left: 2vw">
            <li><spring:theme code="${customer.firstName}" /></li>
            <li><spring:theme code="${customer.lastName}" /></li>
            <li><spring:theme code="${customer.name}" /></li>
            <li><spring:theme code="${customer.uid}" /></li>
            <li><spring:theme code="${customer.customerId}" /></li>
            <li><spring:theme code="${customer.code}" /></li>
            <li><spring:theme code="${customer.externalTokens}" /> - list of external tokens</li>
        </ul>
    </div>
    <div class="row">
        <div class="container-lg col-md-6">
            <div class="account-section-content">
                <div class="account-section-form">
                    <form:form action="update-profile" method="post" commandName="updateProfileForm">

                        <formElement:formSelectBox idKey="profile.title" labelKey="profile.title" path="titleCode" mandatory="true" skipBlank="false" skipBlankMessageKey="form.select.empty" items="${titleData}" selectCSSClass="form-control"/>
                        <formElement:formInputBox idKey="profile.firstName" labelKey="profile.firstName" path="firstName" inputCSS="text" mandatory="true"/>
                        <formElement:formInputBox idKey="profile.lastName" labelKey="profile.lastName" path="lastName" inputCSS="text" mandatory="true"/>

                        <!-- UPDATE & CANCEL buttons -->
                        <div class="row">
                            <div class="col-sm-6 col-sm-push-6">
                                <div class="accountActions">
                                    <ycommerce:testId code="personalDetails_savePersonalDetails_button">
                                        <button type="submit" class="btn btn-primary btn-block">
                                            <spring:theme code="text.account.profile.saveUpdates" text="Save Updates"/>
                                        </button>
                                    </ycommerce:testId>
                                </div>
                            </div>
                            <div class="col-sm-6 col-sm-pull-6">
                                <div class="accountActions">
                                    <ycommerce:testId code="personalDetails_cancelPersonalDetails_button">
                                        <button type="button" class="btn btn-default btn-block backToHome">
                                            <spring:theme code="text.account.profile.cancel" text="Cancel"/>
                                        </button>
                                    </ycommerce:testId>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<style>
    .form {
        border: 2px solid black;
        margin-left: 3vh;
    }
</style>