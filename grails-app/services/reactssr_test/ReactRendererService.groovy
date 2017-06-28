package reactssr_test

import grails.converters.JSON
import grails.transaction.Transactional
import jdk.nashorn.api.scripting.NashornScriptEngine
import org.springframework.core.io.Resource

import javax.script.ScriptEngineManager
// import org.jboss.vfs.VirtualFile

@Transactional
class ReactRendererService {
    static def assetResourceLocator

    private NashornScriptEngine nashornScriptEngine
    private Reader renderPolyfillReader
    private static ReactRendererService reactRendererService =null

    static ReactRendererService getReactRendererSingletonService(){
        if (reactRendererService == null){
            reactRendererService = new ReactRendererService()
            reactRendererService.newNashornEngine()
        }
        return reactRendererService
    }

    NashornScriptEngine getNashornEngine(){
        return this.nashornScriptEngine
    }

    private NashornScriptEngine newNashornEngine(){
        this.nashornScriptEngine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName ("nashorn");
        this.evalFile('nashorn-polyfill.js',this.nashornScriptEngine)
                // evalFile('render-polyfill.js', this.nashornScriptEngine)
                    evalFile('renderPolyfill.entry.js', this.nashornScriptEngine)

//        evalFile('react.min.js',this.nashornScriptEngine)
//        evalFile('react-dom.min.js', this.nashornScriptEngine)
//        evalFile('react-dom-server.min.js', this.nashornScriptEngine)
        // evalFile('vendor.bundle.js', this.nashornScriptEngine)

        // evalFile('vendors.entry.js', this.nashornScriptEngine)
        // evalFile('vendor.entry.js', this.nashornScriptEngine)
        // evalFile('common.entry.js', this.nashornScriptEngine)
//        evalFile('renderPolyfill.entry.js', this.nashornScriptEngine)
//        evalFile('render-polyfill.js', this.nashornScriptEngine)

//        Resource nashornFile= assetResourceLocator.findAssetForURI('nashorn-polyfill.js')
//        Reader nashornPolyFillReader = new InputStreamReader(nashornFile.inputStream)
//
//        Resource renderPolyfill= assetResourceLocator.findAssetForURI('render-polyfill.js')
//        renderPolyfillReader = new InputStreamReader(renderPolyfill.inputStream)
//
//        Resource reactDomFile = assetResourceLocator.findAssetForURI('react-dom.min.js')
//        Reader reactDomReader = new InputStreamReader(reactDomFile.inputStream)
//
////        this.nashornScriptEngine.eval ("load ('${JSFiles.nashornPolyFill.toString()}')")
//        this.nashornScriptEngine.eval (nashornPolyFillReader)
//
//        this.nashornScriptEngine.eval(renderPolyfillReader)

        // evalFile('render-polyfill.js', this.nashornScriptEngine)

        return nashornScriptEngine
    }

    static def evalFile(String fileName, NashornScriptEngine engine){
        // println("into evalFile: " + fileName)
        Resource resource= assetResourceLocator.findAssetForURI(fileName)
        Reader reader = new InputStreamReader(resource.inputStream)
        engine.eval(reader)
        // println("done eval")
    }

    static String renderReact(String jsFile, JSON inData) {
        String finalString = "nothing"

        ReactRendererService reactRendererService1 = getReactRendererSingletonService()
        NashornScriptEngine nashornScriptEngine = reactRendererService1.getNashornEngine()



//        reactRendererService1.evalFile(jsFile,nashornScriptEngine)

//        reactRendererService1.evalFile('renderPolyfill.entry.js',nashornScriptEngine)

//        Resource reactFile = assetResourceLocator.findAssetForURI(jsFile)
//        Reader reader = new InputStreamReader(reactFile.inputStream)
//        nashornScriptEngine.eval(reader)

//        Resource renderPolyfill= assetResourceLocator.findAssetForURI('render-polyfill.js')
//        Reader polyfillReader = new InputStreamReader(renderPolyfill.inputStream)
//        nashornScriptEngine.eval(polyfillReader)//this redefines the global.renderServer
        try {
//            Object html = nashornScriptEngine.invokeFunction ("renderServer", inData.toString())
//            finalString = String.valueOf (html)
// println('file: ' + jsFile)
            // Resource inboundFileResource = assetResourceLocator.findAssetForURI(jsFile)

            // VirtualFile virtualFile = (VirtualFile) inboundFileResource.getContent();

            // File realFile = virtualFile.getPhysicalFile();

            // println('file addres: ' + realFile.getAbsolutePath())

            Object html = nashornScriptEngine.invokeFunction ("loadNewGlobalAndRender",jsFile, 'vendor.bundle.js', inData.toString() )
            finalString = String.valueOf (html)
            // println(finalString)
        }
        catch (Exception e) {
            throw new IllegalStateException ("failed to renderProducts react component", e)
        }
        finalString
    }
}

//enum JSFiles{
//    app2 ("grails-app/assets/javascripts/app2.entry.js"),
//    nashornPolyFill ("grails-app/assets/javascripts/nashorn-polyfill.js")
//
//    private final String name
//
//    private JSFiles(String s) {
//        name = s
//    }
//
//    public boolean equalsName(String otherName) {
//        return (otherName == null) ? false : name.equals(otherName)
//    }
//
//    public String toString() {
//        return this.name
//    }
//}
