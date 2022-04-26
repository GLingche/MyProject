module.exports = {
    pages: {
        index: {
            //入口
            entry: 'src/main.js',
        },
    },
    lintOnSave: true, // 语法检查
    //开启代理服务器（方法一）
    devServer: {
        proxy: 'http://localhost:9090',
    },
    // webpack-dev-server 相关配置


    //开启服务器(方式二)
    // devServer: {
    //     proxy: {
    //         '/api':{
    //             target:'<url>',
    //             pathRewrite:{'^/api':''},
    //             ws:true,//用于支持websocket
    //             changeOrigin: true    //用于控制请求头中的host值
    //         },
    //         '/foo': {
    //             target: '<other_url>'
    //         }
    //     }
    // }        
}