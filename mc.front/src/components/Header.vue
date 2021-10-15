<template>
  <div class="a-row-con box-shadow header">
    <div class="b-row-con" style="height: 40px; width: 15%;">
      <a :href="index_link" class="b-font a-button" style="font-size: 24px">
        <b>(゜-゜)つ凸</b>
      </a>
    </div>
    <div style="width: 35%">
      <el-input :placeholder="default_search_key_words" v-model="search_key_words" class="input-with-select">
        <el-select v-model="search_option" slot="prepend" style="width: 100px">
          <el-option v-for="item in search_options"
            :label="item.label"
            :value="item.key"
            :key="item.key" class="c-button">
          </el-option>
        </el-select>
        <el-button type="primary" plain slot="append" icon="el-icon-search" v-on:click="search"></el-button>
      </el-input>
    </div>
    <div style="width: 15%"></div>
  </div>
</template>
<script>
import '../assets/globle.css';
import {getRequest} from '../js/http.js'

export default {
  name: 'Header',
  data() {
    return {
      search_options:[
        {
          label: '视频',
          key: '1'
        },
        {
          label: '用户',
          key: '2'
        },
        {
          label: '直播间',
          key: '3'
        }
      ],
      search_option: '1',
      search_key_words: '',
      default_search_key_words: 'default key words',
      login_success: true,
      user_id: '',
      user_avatar: '',
      personal_page: '',
      message_page: '',
      message_count:12,
      header_describe: '',
      index_link: ''
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init(){
      this.index_link = this.GLOBAL.FRONT_URL + "/index.html"
      this.personal_page = this.GLOBAL.BACK_URL + "/personal.html"
      this.message_page = this.GLOBAL.BACK_URL + "/message.html"
      const GET_INFO_URL = this.GLOBAL.BACK_URL + "/user/header/getHeaderInfo"
      const LOG_OUT_URL = this.GLOBAL.BACK_URL + "/user/identity/logout"
      getRequest(GET_INFO_URL).then(res=>{
        if(res.data.hasLogin){
          this.login_success = true;
          this.user_avatar = res.data.avatarSrc;
          this.user_id = res.data.id;
          this.header_describe = res.data.userName;
        }else {
          this.header_describe = "登录/注册";
        }
      }).catch(error=>{
        console.log(error.response)
      })
    },
    search(){
      if(this.search_key_words.length===0){
        this.search_key_words = this.default_search_key_words;
      }
      console.log(this.search_option);
      console.log(this.search_key_words);
      this.search_key_words=''
    }
  }
}
</script>

<style>
</style>
