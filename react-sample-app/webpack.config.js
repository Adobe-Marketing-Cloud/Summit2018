var CopyWebpackPlugin = require('copy-webpack-plugin');
const HtmlWebpackPlugin = require("html-webpack-plugin");

var plugins = [];
plugins.push(new HtmlWebpackPlugin({
    chunks: ["bundle"],
    filename: "index.html",
    template: "src/template.html"
}));
plugins.push(new CopyWebpackPlugin([
    {
        from: 'src/images',
        to: 'images'
    },
    {
        from: 'src/stylesheets',
        to: 'stylesheets'
    }
]));
module.exports = {
    entry: "./src/index.js",
    plugins: plugins,
    output: {
        path: "build/",
        filename: "bundle.min.js"
    },
    devServer: {
        inline: true,
        contentBase: './build',
        port: 3000
    },
    module: {
        loaders: [
            {
                test: /\.js$/,
                exclude: /(node_modules)/,
                loader: ['babel'],
                query: {
                    presets: ['es2015', 'react', 'stage-0']
                }
            }
        ]
    }
}