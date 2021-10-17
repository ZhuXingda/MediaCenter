import Vue from "vue";
import VueRouter from "vue-router";

// 引入组件
import DisplayTable from "../components/DisplayTable";
import DisplayRow from "../components/DisplayRow";
import Login from "../subpage/Login";
import VideoPage from "../subpage/VideoPage";
import Index from "../subpage"

// 要告诉 vue 使用 vueRouter
Vue.use(VueRouter);

const routes = [
  {
    name:"index",
    path:"/",
    component: Index
  },
  {
    name: "subscribe",
    path: "/DisplayTable",
    component: DisplayTable
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
  }
]

const router =  new VueRouter({
  routes
})
export default router;
