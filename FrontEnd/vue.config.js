const path = require('path')
function resolve(dir) {
  return path.join(__dirname, dir)
}

// vue.config.js
module.exports = {
  productionSourceMap: false,

  //打包app时放开该配置
  // publicPath:'./',
  // baseUrl: './',
  configureWebpack: config => {
    //生产环境取消 console.log
    if (process.env.NODE_ENV === 'production') {
      config.optimization.minimizer[0].options.terserOptions.compress.drop_console = true
    }
  },
  chainWebpack: (config) => {
    config.resolve.alias
      .set('@$', resolve('src'))
      .set('@api', resolve('src/api'))
      .set('@assets', resolve('src/assets'))
      .set('@style', resolve('src/styles'))

    // 配置 webpack 识别 markdown 为普通的文件
    config.module
      .rule('markdown')
      .test(/\.md$/)
      .use()
      .loader('file-loader')
      .end()
  },

  devServer: {
    port: 8080,
    proxy: {
      '/service': {
        target: 'http://localhost:9999',//请求本地
        changeOrigin: true,
        secure: false,
        pathRewrite: {
          '^/service': ''
        }
      },
    }
  },
  lintOnSave: false
}
