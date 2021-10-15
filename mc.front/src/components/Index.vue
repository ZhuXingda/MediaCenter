<template>
  <div class="row-body">
    <SideBar v-on:content_change="content_change" style="width: 130px"/>
    <div style="width: 800px">
      <transition name="el-fade-in-linear">
        <DisplayTable v-show="navigator===1"/>
      </transition>
      <transition name="el-fade-in">
        <Login v-show="navigator===8"/>
      </transition>
    </div>
  </div>
</template>

<script>
import DisplayTable from "./DisplayTable";
import SideBar from "./SideBar";
import Login from "./Login";
export default {
  name: "Index",
  components: {
    DisplayTable,
    SideBar,
    Login
  },
  data(){
    return {
      navigator:1,
    }
  },
  methods:{
    content_change(content){
      this.navigator = content;
    },
    //type 分成 videoList subscription videoDetail
    //searchResult message personalCenter
    content_show(type, key){

    }
  }
}
</script>

<style scoped>
</style>
