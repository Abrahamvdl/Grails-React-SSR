import reactssr_test.ReactRendererService

// Place your Spring DSL code here
beans = {
    ReactRendererService(ReactRendererService){
        assetResourceLocator = ref('assetResourceLocator')
    }

}
