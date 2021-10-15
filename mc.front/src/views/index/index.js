// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from "./App.vue";
import global from '../../components/Common';
import router from "../../js/router"

Vue.component('font-awesome-icon', global.FontAwesomeIcon)
Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.prototype.GLOBAL = global
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
