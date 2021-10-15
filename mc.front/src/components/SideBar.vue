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
    <div class="b-col-con" style="align-items: start; margin: 0 20px 20px; width: 80%">
      <el-button type="text" class="c-button b-row-con" v-on:click="side_bar_change(1)" v-bind:class="{ 'c-chosen' : chosen_option===1 }">
        <i class="el-icon-house bar-icon"></i>
        <a>热门推荐</a>
      </el-button>
      <el-button type="text" class="c-button b-row-con" v-on:click="side_bar_change(2)" v-bind:class="{ 'c-chosen' : chosen_option===2 }">
        <i class="el-icon-bell bar-icon"></i>
        <a>全部关注</a>
      </el-button>
      <el-button type="text" class="c-button b-row-con" v-on:click="side_bar_change(3)" v-bind:class="{ 'c-chosen' : chosen_option===3 }">
        <i class="el-icon-video-play bar-icon"></i>
        <a>原创视频</a>
      </el-button>
      <el-button type="text" class="c-button b-row-con" v-on:click="side_bar_change(4)" v-bind:class="{ 'c-chosen' : chosen_option===4 }">
        <i class="el-icon-monitor bar-icon"></i>
        <a>电影电视剧</a>
      </el-button>
      <el-button type="text" class="c-button b-row-con" v-on:click="side_bar_change(5)" v-bind:class="{ 'c-chosen' : chosen_option===5 }">
        <i class="el-icon-video-camera bar-icon"></i>
        <a>直播</a>
      </el-button>
      <el-button type="text" class="c-button b-row-con" v-on:click="side_bar_change(6)" v-bind:class="{ 'c-chosen' : chosen_option===6 }">
        <i class="el-icon-message bar-icon"></i>
        <a>消息</a>
        <el-badge v-if="message_count>0" class="mark" type="primary" :max="99" :value="message_count" />
      </el-button>
      <el-button type="text" class="c-button b-row-con" v-on:click="side_bar_change(7)" v-bind:class="{ 'c-chosen' : chosen_option===7 }">
        <i class="el-icon-user bar-icon"></i>
        <a>个人中心</a>
      </el-button>
      <el-button type="text" class="c-button b-row-con" v-show="login_status>0" v-on:click="side_bar_change(7)">
        <i class="el-icon-refresh bar-icon"></i>
        <a>切换账号</a>
      </el-button>
      <el-button type="text" class="c-button b-row-con" v-show="login_status<1" v-on:click="side_bar_change(8)">
        <i class="el-icon-refresh bar-icon"></i>
        <a>登录/注册</a>
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
      message_count : 200,
      login_status : 0,
      user_name: '未登录',
      fol_count: 0,
      sub_count: 0,
      pub_count: 0,
      user_avatar: ''
    }
  },
  props:[
    'chosen_option'
  ],
  methods:{
    side_bar_change(index){
      this.chosen_option = index;
      this.$emit('content_change', this.chosen_option)
      if(index===6){
        this.message_count=0;
      }
      if(index===8){
        index = 7;
        this.login_status=0
      }
      if(index===7){
        index = 8
        this.login_status=1
      }
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
