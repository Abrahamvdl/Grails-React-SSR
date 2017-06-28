global.loadNewGlobalAndRender = function(fileLocation, vendorFileLocation, inBoundData) {
    var newFileLocation = "grails-app/assets/javascripts/" + fileLocation //add the relative path.
    var newVendorLocation = "grails-app/assets/javascripts/" + vendorFileLocation

    var scriptObject = {
        name: "reactLoader",
        script:
        "Object.bindProperties(this,arguments[0]);load(arguments[1]);renderServer(arguments[2]);"
    };
    return loadWithNewGlobal(scriptObject, this, newFileLocation, inBoundData, newVendorLocation);
};
