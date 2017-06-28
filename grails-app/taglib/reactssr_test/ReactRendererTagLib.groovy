package reactssr_test

class ReactRendererTagLib {
    def renderReact = { attrs, body ->
        if (!attrs.jsFile) {
            throw new IllegalArgumentException('Javascript file cannot be null')
        }

        if(!attrs.inData){
            throw new IllegalArgumentException('In Data cannot be null')
        }
        //TODO: add some check to ensure we get proper JSON data and as proper filename

        out << render(template: "/Templates/reactRender", model: ['jsFile': attrs.jsFile, 'inData': attrs.inData])
    }
}
