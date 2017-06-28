<%--
  Created by IntelliJ IDEA.
  User: abraham
  Date: 2017/06/23
  Time: 5:02 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Try 4</title>
</head>

<body>
<p>a Totaly new Taglib test</p>
<g:renderReact jsFile="${'singing.entry.js'}" inData="${[singing: 'la la la la '] as grails.converters.JSON}"/>

</body>
</html>