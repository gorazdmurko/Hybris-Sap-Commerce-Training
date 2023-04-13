<%--
  Created by IntelliJ IDEA.
  User: Gorazd
  Date: 16. 12. 2022
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/responsive/user" %>


<html>
    <head>
        <title>Custom Paragraph Component</title>
    </head>

    <body>
        <div class="container" style="background-color: #eee;">
            <div>
                <h1 style="color: violet;">CUSTOM PARAGRAPH COMPONENT</h1>
            </div>

            <div class="custom-paragraph">
                <div id="header">
                    <h4>Header: ${headerSection}</h4>
                </div>
                <div id="content">
                    <h3>Content: ${content}</h3>
                </div>
                <div id="footer">
                    <h4>Footer: ${footerSection}</h4>
                </div>
                <hr />
            </div>
        </div>
    </body>
</html>

<style>
    .btn-consent {
        min-width: 28rem;
    }
</style>