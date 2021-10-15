import Vue from "vue";
import VueRouter from "vue-router";

// 引入组件
import DisplayTable from "../components/DisplayTable";
import DisplayRow from "../components/DisplayRow";
import Login from "../components/Login";
import VideoPage from "../components/VideoPage";

// 要告诉 vue 使用 vueRouter
Vue.use(VueRouter);

const routes = [
  {
    path:"/DisplayTable",
    component: DisplayTable
  },
  {
    path: "/DisplayRow",
    component: DisplayRow
  },
  {
    path: "/Login",
    component: Login
  },
  {
    path: "/VideoPage",
    component: VideoPage
  },
  {
    path:"/",
    component: DisplayTable
  }
]

const router =  new VueRouter({
  routes
})
export default router;
