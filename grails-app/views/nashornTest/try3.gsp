<%--
  Created by IntelliJ IDEA.
  User: abraham
  Date: 2017/06/21
  Time: 11:44 AM
--%>

<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
    <p>Taglib test</p>
    <g:renderReact jsFile="${'app2.entry.js'}" inData="${[show: 'this is some new new data'] as grails.converters.JSON}"/>
</body>
</html>
