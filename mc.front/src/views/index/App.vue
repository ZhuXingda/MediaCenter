<template>
  <div class="page">
    <Header/>
    <div class="row-body">
      <SideBar v-on:content_change="content_change" v-bind:chosen_option="this.index" style="width: 200px"/>
<!--      <div style="width: 900px">
        <transition name="el-fade-in">
          <DisplayTable v-if="index===1" columnStyle="index"/>
        </transition>
        <transition name="el-fade-in">
          <DisplayRow v-on:content_forward="content_forward" v-on:content_backward="content_backward" v-if="index===2" columnStyle="index"/>
        </transition>
        <transition name="el-fade-in">
          <DisplayTable v-if="index===3" columnStyle="origin"/>
        </transition>
        <transition name="el-fade-in">
          <DisplayTable v-if="index===4" columnStyle="theater"/>
        </transition>
        <transition name="el-fade-in">
          <DisplayTable v-if="index===5" columnStyle="live"/>
        </transition>
        <transition name="el-fade-in">
          <Login v-if="index===6"/>
        </transition>
        <transition name="el-fade-in">
          <VideoPage v-if="index===8" columnStyle="index"/>
        </transition>
      </div>-->
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import Header from "../../components/Header"
import DisplayTable from "../../components/DisplayTable";
import DisplayRow from "../../components/DisplayRow";
import SideBar from "../../components/SideBar";
import Login from "../../components/Login";
import VideoPage from "../../components/VideoPage";

export default {
  name: 'App',
  components: {
    VideoPage,
    Header,
    DisplayTable,
    DisplayRow,
    SideBar,
    Login
  },
  data(){
    return {
      index:1,
      previous:1,
    }
  },
  methods:{
    content_forward(index){
      console.log('content_forward')
      this.previous = this.index;
      this.index = index;
    },
    content_backward(){
      this.index = this.previous;
      window.location.href = ""
    },
    content_change(index){
      this.index = index;
    },
    //type 分成 videoList subscription videoDetail
    //searchResult message personalCenter
    content_show(type, key){

    }
  }
}
</script>
