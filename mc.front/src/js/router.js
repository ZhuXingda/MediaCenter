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
    name:"index",
    path:"/DisplayTable",
    component: DisplayTable
  },
  {
    name:"index",
    path:"/DisplayTable",
    component: DisplayRow
  },
  {
    name: "origin",
    path: "/DisplayRow",
    component: DisplayTable
  },
  {
    name: "movie",
    path: "/DisplayRow",
    component: DisplayTable
  },
  {
    name: "live",
    path: "/DisplayRow",
    component: DisplayTable
  },
  {
    name:"message",
    path:"/DisplayTable",
    component: DisplayRow
  },
  {
    name: "personal",
    path: "/Login",
    component: Login
  },
  {
    path: "/",
    component: DisplayTable
  }
]

const router =  new VueRouter({
  routes
})
export default router;
