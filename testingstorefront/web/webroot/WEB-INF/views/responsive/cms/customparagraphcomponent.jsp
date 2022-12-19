<%--
  Created by IntelliJ IDEA.
  User: Gorazd
  Date: 16. 12. 2022
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <h1>Header: ${headerSection}</h1>
                <h3>Content: ${content}</h3>
                <h6>Footer: ${footerSection}</h6>
                <hr />
            </div>
        </div>
    </body>
</html>
