import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import * as $ from 'jquery'
import * as echarts from 'echarts'
import '@style/global.scss'

if (!window.jQuery) {
  window.jQuery = $
}
Vue.prototype.$echarts = echarts
Vue.prototype.$url = 'http://localhost:9999'
Vue.prototype.$currentUser={}
Vue.config.productionTip = false
Vue.use(ElementUI);
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
