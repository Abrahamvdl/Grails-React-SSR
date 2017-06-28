<%@ page import="reactssr_test.ReactRendererService" %>
<div id="content">
    ${raw(reactssr_test.ReactRendererService.renderReact(jsFile,inData))}
</div>

<!-- <asset:javascript src="vendor.bundle.js"/> -->
<asset:javascript src="${jsFile}"/>

<g:javascript>
    <g:applyCodec encodeAs="none">
        window.inData = '${inData}';
    </g:applyCodec>

    // window.renderClient = function(inData) {
    //     var jsData = JSON.parse(inData);
    //     React.render (
    //         React.createElement('App',{data:jsData},null),
    //         document.getElementById ('content')
    //     );
    // };

    window.renderClient(inData);
</g:javascript>
