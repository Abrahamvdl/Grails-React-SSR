package reactssr_test

import grails.converters.JSON
import grails.transaction.Transactional
import jdk.nashorn.api.scripting.NashornScriptEngine
import org.springframework.core.io.Resource

import javax.script.ScriptEngineManager

@Transactional
class ReactRendererService {
    static def assetResourceLocator

    private NashornScriptEngine nashornScriptEngine
    private Reader renderPolyfillReader
    private static ReactRendererService reactRendererService =null //singleton that keeps the scripting engine warm.

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
        this.evalFile('renderPolyfill.entry.js', this.nashornScriptEngine)
        return nashornScriptEngine
    }

    static def evalFile(String fileName, NashornScriptEngine engine){
        Resource resource= assetResourceLocator.findAssetForURI(fileName)
        Reader reader = new InputStreamReader(resource.inputStream)
        engine.eval(reader)
    }

    static String renderReact(String jsFile, JSON inData) {
        String finalString = "nothing"

        try {
            Object html = getReactRendererSingletonService().
              getNashornEngine().
              invokeFunction (
                "loadNewGlobalAndRender",
                jsFile,
                'vendor.bundle.js',
                inData.toString()
              )
            finalString = String.valueOf (html)
        }
        catch (Exception e) {
            throw new IllegalStateException ("failed to renderProducts react component", e)
        }
        finalString
    }
}
