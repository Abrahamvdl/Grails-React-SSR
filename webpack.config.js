const path = require('path');
const webpack = require('webpack');

module.exports = {
    context: path.resolve(__dirname, './'),
    // context: path.join(__dirname,'node_modules'),
    entry: {
        app: "./src/main/js/component/reacttest.js",
        app2:"./src/main/js/component/reacttest2.js",
        singing:"./src/main/js/component/reacttest3.js",
        renderPolyfill:"./src/main/js/component/render-polyfill.js",
        // vendor: ['react','react-dom'],
    },
    output:{
        path: path.resolve(__dirname, './grails-app/assets/javascripts/'),
        // path: './grails-app/assets/javascripts',
        publicPath: "/assets/",
        filename: "[name].entry.js",
        // library:"ServerSide"

    },
    module: {
        // context: path.join(__dirname,'node_modules'),
        rules: [
            {
                test: /\.js$/,
                exclude:[/node_modules/],
                use: [{
                    loader: 'babel-loader',
                    options: {presets: ['es2015']},
                }],
                // include: path.join(__dirname, 'src/main/js'),
                //
                // loader: 'babel',
                // query: {
                //     presets: ['es2015', 'react']
                // }
            }
        ]
    },
    plugins: [
      new webpack.DefinePlugin({
        'process.env': {
          NODE_ENV: JSON.stringify('production') //production
        }
      }),
      new webpack.optimize.UglifyJsPlugin(),
        // new webpack.optimize.CommonsChunkPlugin({
        //   name:'vendor',
        //   // minChunks:2,
        //   filename:'./vendor.bundle.js'
        // })
    ]
};
