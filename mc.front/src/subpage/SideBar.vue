<template>
  <div class="a-col-con box-shadow">
    <div class="b-col-con">
      <el-button type="text" style="margin: 10px;padding: 0">
        <div class="c-row-con" style="justify-content: center" v-on:click="side_bar_change(7)">
          <div class="avatar-wrapper">
            <img class="avatar" :src="this.user_avatar" v-if="login_status===1"/>
            <i class="el-icon-user b-font" style="font-size: 25px" v-if="login_status===0"></i>
            <div class="avatar-mask"></div>
          </div>
          <div class="b-font a-button" style="padding-left: 10px">{{ user_name }}</div>
        </div>
      </el-button>
      <div class="b-row-con">
        <a class="c-col-con a-font a-button" href="http://www.baidu.com">
          <span style="font-size: 14px; font-weight: bold">{{ sub_count }}</span>
          <span style="font-size: 12px">关注</span>
        </a>
        <a class="c-col-con a-font a-button" href="http://www.baidu.com">
          <span style="font-size: 14px; font-weight: bold">{{ fol_count }}</span>
          <span style="font-size: 12px">粉丝</span>
        </a>
        <a class="c-col-con a-font a-button" href="http://www.baidu.com">
          <span style="font-size: 14px; font-weight: bold">{{ pub_count }}</span>
          <span style="font-size: 12px">动态</span>
        </a>
      </div>
    </div>
    <el-divider></el-divider>
    <div class="b-col-con" style="align-items: start; margin: 0 20px 20px; width: 80%" v-for="option in side_bar_options">
      <el-button type="text" class="c-button b-row-con" @click="option_select(option)" v-bind:class="{ 'c-chosen' : chosen_option===option.router_name }">
        <i class="bar-icon" v-bind:class="option.option_icon"></i>
        <a>{{ option.option_name }}</a>
      </el-button>
    </div>
  </div>
</template>

<script>
import {getRequest} from "../js/http";
import '../assets/globle.css'

export default {
  name: "SideBar",
  data() {
    return {
      side_bar_options:[
        {
          router_name:'index',
          option_icon:'el-icon-house',
          option_name:'热门推荐'
        },
        {
          router_name:'subscribe',
          option_icon:'el-icon-bell',
          option_name:'全部关注'
        },
        {
          router_name:'origin',
          option_icon:'el-icon-video-play',
          option_name:'原创视频'
        },
        {
          router_name:'movie',
          option_icon:'el-icon-monitor',
          option_name:'电影电视剧'
        },
        {
          router_name:'live',
          option_icon:'el-icon-video-camera',
          option_name:'直播'
        },
        {
          router_name:'message',
          option_icon:'el-icon-message',
          option_name:'消息'
        },
        {
          router_name:'personal',
          option_icon:'el-icon-user',
          option_name:'个人中心',
          router_params:{
            user_id:'1234132451'
          }
        }
      ],
      message_count : 200,
      login_status : 0,
      user_name: '未登录',
      fol_count: 0,
      sub_count: 0,
      pub_count: 0,
      user_avatar: '',
      chosen_option : 'index'
    }
  },
  methods:{
    option_select(option){
      this.$router.push({
        name: option.router_name,
        params: option.router_params
      })
      this.chosen_option = option.router_name
    },
    logout(){
      this.$alert('确定退出当前账号？', '切换账号', {
        confirmButtonText: '确定',
        callback: action => {
          this.login_status=0
          this.$message({
            type: 'info',
            message: `action: ${ action }`
          });
        }
      });
    }
  }
}
</script>

<style scoped>
.a-col-con{
  position: sticky;
  position: -webkit-sticky;
  top: 80px;
  margin-top: 20px;
  padding: 10px 5px;
}
.c-chosen{
  color: #409EFF;
  background-color: #F2F6FC;
}
.c-button{
  width: 100%;
  padding: 0;
  margin-left: 0;
}
.b-row-con{
  justify-content: start;
  padding: 0 10px;
  height: 50px;
}
.c-col-con{
  margin: 0 10px;
}
.bar-icon{
  margin-right: 5px;
  font-weight: bolder;
}
</style>
