<%@ page import="reactssr_test.ReactRendererService" %>
<div id="content">
    ${raw(reactssr_test.ReactRendererService.renderReact(jsFile,inData))}
</div>

<asset:javascript src="${jsFile}"/>
<g:javascript>
    <g:applyCodec encodeAs="none">
        window.inData = '${inData}';
    </g:applyCodec>
    window.renderClient(inData);
</g:javascript>
