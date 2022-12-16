<%--
  Created by IntelliJ IDEA.
  User: Gorazd
  Date: 23. 09. 2022
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Video Component</title>
    </head>
    <body>
        <div>
            <iframe
                width="${width}"
                height="${height}"
                src="https://www.youtube.com/embed/${videoId}?autoplay=${autoplay}&controls=${showControls}"
            >
            </iframe>
            <h1>Video id: ${videoId}</h1>
            <h2>Width: ${width}</h2>
            <h2>Height: ${height}</h2>
            <h2>Autoplay: ${autoPlay}</h2>
            <h2>Show controls: ${showControls}</h2>
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