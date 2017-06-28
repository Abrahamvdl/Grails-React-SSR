package reactssr_test

class ReactRendererTagLib {
//    static defaultEncodeAs = [taglib:'html']
//    static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
//    static defaultEncodeAs = "raw"
    def renderReact = { attrs, body ->
        if (!attrs.jsFile) {
            throw new IllegalArgumentException('Javascript file cannot be null')
        }

        if(!attrs.inData){
            throw new IllegalArgumentException('In Data cannot be null')
        }

//        StringBuilder builder = new StringBuilder()
//
//        builder.append("""
//            <div id="content">
//        """)
//
//        builder.append(ReactRendererService.renderReact(attrs.jsFile, attrs.inData))
//
//        builder.append("</div>")
//
//
//        builder.append("""
//            <script type="text/javascript" src="/assets/app2.entry.js" ></script>
//        """)

//        builder.append("""
//            <script >
//                window.inData = '${attrs.inData}';
//                window.renderClient (inData);
//            </script>
//        """)

//        builder.append(asset.javascript(src: attrs.jsFile))

//        out << ReactRendererService.renderReact(attrs.jsFile,attrs.inData)

        // println ("indata: " + attrs.inData)

        out << render(template: "/Templates/reactRender", model: ['jsFile': attrs.jsFile, 'inData': attrs.inData])
//        out << builder.toString()
    }
}
