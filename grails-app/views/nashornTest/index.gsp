<%--
  Created by IntelliJ IDEA.
  User: abraham
  Date: 2017/06/13
  Time: 1:03 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>The Test</title>

    %{--<asset:javascript src="react.min.js"/>--}%
    %{--<asset:javascript src="jquery-2.2.0.min.js"/>--}%
</head>

<body>
<p>Tests come here</p>

<div id="content">${raw(content)}</div>

%{--<asset:javascript src="reacttest.js"/>--}%
<asset:javascript src="app2.entry.js"/>
%{--<g:javascript>--}%
        %{--// $(function(){--}%
        %{--//     renderClient(${data});--}%
        %{--// });--}%
%{--</g:javascript>--}%

%{--<script th:inline="javascript">--}%
    %{--window.INITIAL_PRODUCTS = [[${initialProducts}]];--}%
    %{--window.INITIAL_SORTBY = [[${initialSortBy}]];--}%
    %{--window.renderClient ();--}%
%{--</script>--}%

<g:javascript>
    window.renderClient ();
</g:javascript>
</body>
</html>