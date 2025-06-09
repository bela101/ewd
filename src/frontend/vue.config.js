const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 3000,
    https: true,
    proxy: {
      '/api': {
        target: 'https://localhost:8443',
        ws: true,
        changeOrigin: true,
        secure: false,
      },
      '/images': {  // Add this new proxy configuration
        target: 'https://localhost:8443',
        changeOrigin: true,
        secure: false,
      }
    }
  },
})