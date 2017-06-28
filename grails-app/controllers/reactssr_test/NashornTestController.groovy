package reactssr_test

import jdk.nashorn.api.scripting.NashornScriptEngine
import jdk.nashorn.api.scripting.ScriptObjectMirror

import javax.script.ScriptEngineManager
import org.springframework.core.io.Resource

class NashornTestController {
    def assetResourceLocator

    def index() {
        NashornScriptEngine nashorn = (NashornScriptEngine)new ScriptEngineManager().getEngineByName("nashorn")

//        Resource reactFile = assetResourceLocator.findAssetForURI('/reacttest.js')
        Resource reactFile = assetResourceLocator.findAssetForURI('/app.entry.js')
//        Resource reactServer = assetResourceLocator.findAssetForURI('/react.min.js')
        Resource polyfill = assetResourceLocator.findAssetForURI('/nashorn-polyfill.js')

//        SequenceInputStream sis = new SequenceInputStream(polyfill.inputStream, reactServer.inputStream)
//
//        SequenceInputStream sis2 = new SequenceInputStream(sis, reactFile.inputStream)
        SequenceInputStream sis2 = new SequenceInputStream(polyfill.inputStream, reactFile.inputStream)

        Reader reader = new InputStreamReader(sis2)

//        nashorn.eval(reader)

//        println ("nashorn output: " + renderReactTest(nashorn,["From Server"]))

        [content: renderReactTest(nashorn,reader,["from Server"])]
    }

    Reader read(String path){
        InputStream inStream = getClass().getClassLoader().getResourceAsStream(path)
        new InputStreamReader(inStream)
    }

    String renderReactTest(NashornScriptEngine nashorn, Reader reader , indata){
        try {
            ScriptObjectMirror root = nashorn.eval(reader)
            ScriptObjectMirror serverSide2 = nashorn.eval("ServerSide")
            ScriptObjectMirror serverSide = nashorn.invokeMethod(serverSide2,"renderServer",indata)

//            println( "something: "+ root.call("ServerSide"))

//            ScriptObjectMirror serverSide = root.call("ServerSide")
//            println("Object: " + root.getClassName)
//            println("Object keys: " + root.getOwnKeys(true))
//            println("Object toString: " + root.toString())

//            println("name: " + root.callMember("ServerSide"))

            println("Now we try serverSide")
//            ScriptObjectMirror serverSide = nashorn.invokeFunction("ServerSide")
//            ScriptObjectMirror serverSide = nashorn.invokeMethod("ServerSide","renderServer")
            println("Object: " + serverSide.getClassName)
            println("Object keys: " + serverSide.getOwnKeys(true))
            println("Object toString: " + serverSide.toString())

//            Object html = nashorn.invokeMethod(root, "renderServer",indata)
//            Object html = nashorn.invokeFunction("renderServer", indata)
//              Object html = nashorn.invokeFunction("server","renderServer",indata)
            return String.valueOf(html)
        }catch (Exception e){
            throw new IllegalStateException("failed to render react component", e)
        }
    }

    def try2(){
        String finalString = "nothing"

        NashornScriptEngine nashornScriptEngine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName ("nashorn");
        nashornScriptEngine.eval ("load ('grails-app/assets/javascripts/nashorn-polyfill.js')")
        nashornScriptEngine.eval ("load ('grails-app/assets/javascripts/app2.entry.js')")

        try {
            Object html = nashornScriptEngine.invokeFunction ("renderServer", [show:'from Server'] as grails.converters.JSON)
//            return String.valueOf (html)
            finalString = String.valueOf (html)
        }
        catch (Exception e) {
            throw new IllegalStateException ("failed to renderProducts react component", e)
        }

        render view:"index", model: [content: finalString]
    }

    def try3(){

    }

    def try4(){

    }
}
