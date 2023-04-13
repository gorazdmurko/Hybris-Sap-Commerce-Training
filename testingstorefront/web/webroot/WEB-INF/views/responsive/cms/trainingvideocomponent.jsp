<%--
  Created by IntelliJ IDEA.
  User: Gorazd
  Date: 23. 09. 2022
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/responsive/action" %>

<html>
<script src="https://code.jquery.com/jquery-2.2.4.js" integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" crossorigin="anonymous"></script>
    <head>
        <title>Video Component</title>
    </head>

    <body class="container">
        <div id="video" class="container" style="background-color: #eee;">
            <div>
                <hr />
                <h1 style="color: violet;">TRAINING VIDEO COMPONENT</h1>
            </div>

            <div>
                <action:actions element="div" parentComponent="${component}" />
                <hr />
            </div>

            <div class="custom-video">
<%--                <iframe width="${width}" height="${height}" src="${videoUrl}"></iframe>--%>
                <iframe
                        width="${width}"
                        height="${height}"
                        src="https://www.youtube.com/embed/${videoId}?autoplay=${autoplay}&controls=${showControls}"
                >
                </iframe>
            </div>
        </div>
    </body>
</html>



<%--
    IMPEX

    $contentCatalog=electronicsContentCatalog
    $contentCatalogName=Electronics Content Catalog
    $contentCV=catalogVersion(CatalogVersion.catalog(Catalog.id[default=$contentCatalog]),CatalogVersion.version[default=Staged])[default=$contentCatalog:Staged]

    INSERT_UPDATE TrainingVideoComponent;$contentCV[unique=true];uid[unique=true];name;width;height;videoId;autoPlay;showControls;&componentRef
    ;;HomePageVideoCMSComponent;Our First CMS Component;1250;500;3XK9Ea9Idc0;false;true;HomePageVideoCMSComponent

    UPDATE ContentSlot;$contentCV[unique=true];uid[unique=true];active;cmsComponents(&componentRef)
    ;;Section1Slot-Homepage;true;HomePageVideoCMSComponent
--%>