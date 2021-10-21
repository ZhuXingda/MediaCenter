import Vue from "vue";
import VueRouter from "vue-router";

// 引入组件
import DisplayTable from "../components/DisplayTable";
import Subscription from "../subpage/Subscription";
import Login from "../subpage/Login";
import VideoPage from "../subpage/VideoPage";
import Index from "../subpage"
import VideoTable from "../subpage/VideoTable";
import MovieTable from "../subpage/MovieTable";
import MessagePage from "../subpage/MessagePage";

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
    path: "/subscribe",
    component: Subscription
  },
  {
    name: "origin",
    path: "/origin",
    component: VideoTable
  },
  {
    name: "movie",
    path: "/movie",
    component: MovieTable
  },
  {
    name: "live",
    path: "/DisplayRow",
    component: DisplayTable
  },
  {
    name:"message",
    path:"/message",
    component: MessagePage
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
