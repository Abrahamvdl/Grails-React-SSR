// import React from 'react';
// import { renderToString } from 'react-dom/server'
//
// global.renderServer = function(newData) {
//     var jsData = JSON.parse(newData);
//     return renderToString (
//         React.createElement('App',{data:jsData},null)
//         // <App data={jsData}/>
//     )
// };

// var scriptObject = {
//     name: "reactLoader",
//     script:"var global = arguments[0];" +
//         "load(arguments[1]);"+
//         "renderServer(arguments[2]);"
//
// };
//
// var output = loadWithNewGlobal(scriptObject,this, placeholderForFileLocation, placeholderForInboundData);

global.loadNewGlobalAndRender = function(fileLocation, vendorFileLocation, inBoundData) {
    // print("we are in the loadGlobal");

    var newFileLocation = "grails-app/assets/javascripts/" + fileLocation //add the relative path.
    var newVendorLocation = "grails-app/assets/javascripts/" + vendorFileLocation

    var scriptObject = {
        name: "reactLoader",
        script:
        // "print('we are inside the new environment');" +
            // "print ('main Global: ' + arguments[0]);  " +
            // "print('setting the global now');"+

        // "var global = arguments[0].global;" +
        // "this = arguments[0];" +

        "Object.bindProperties(this,arguments[0]);"+
        // "print('new global after: ' + this);" +
            // "print('loading the file now');"+

            // "load(arguments[3]);" +

        "load(arguments[1]);" +
            // "print('doing the render now');" +
        "renderServer(arguments[2]);" +
        ""
    };

    return loadWithNewGlobal(scriptObject, this, newFileLocation, inBoundData, newVendorLocation);
};
