<template>
  <div class="a-col-con box-shadow">
    <div class="a-col-con" style="width: 100%">
      <div class="b-col-con">
        <h1>{{ video_name }}</h1>
        <p class="a-font">{{ video_date }}</p>
      </div>
      <el-divider></el-divider>
      <div class="b-col-con" id="video_player_box">
        <VideoPlayer/>
      </div>
      <el-divider></el-divider>
      <div class="b-col-con">
        <dl class="b-row-con">
          <dt v-for="tag in video_tags" style="padding-right: 10px;">
            <a :href="tag.href">
              <el-tag class="b-button">
                {{ tag.name }}
              </el-tag>
            </a>
          </dt>
        </dl>
        <div class="b-col-con">
          <div class="a-font">
            {{video_describe_brief}}
            <el-collapse-transition>
              <div v-show="describe_show" class="paragraph">
                {{video_describe_more}}
              </div>
            </el-collapse-transition>
          </div>
        </div>
      </div>
      <div style="width: 100%; text-align: center;">
        <a v-on:click="describe_fold()" class="a-font a-button" style="font-size: 27px">
          <font-awesome-icon :icon="click_word"/>
        </a>
      </div>
    </div>
    <Comment style="width: 100%"/>
  </div>
</template>

<script>
import '../assets/globle.css'
import VideoPlayer from "./VideoPlayer"
import Comment from "./Comment";
import {getRequest, getQuery} from '../js/http.js'

const SERVICE_URL = '/video'
const VIDEO_ID = getQuery('video_id');

export default {
  name: "Video",
  data() {
    return {
      video_name: "",
      video_date: "",
      video_tags: [
        {
          name: "",
          href: ""
        }
      ],
      video_describe_brief: "",
      video_describe_more: "",
      click_word: "angle-down",
      describe_show: false
    }
  },
  mounted() {
    this.init()
  },
  methods:{
    init: function (){
      const URL = this.BACK_URL + SERVICE_URL
      const params = {
        video_id : VIDEO_ID
      }
      getRequest(URL,params).then(res=>{
        this.video_name = res.video_name;
        this.video_date = res.video_date;
        this.video_tags = res.video_tags;
        this.video_describe_brief = res.video_describe_brief;
        this.video_describe_more = res.video_describe_more;
      })
    },
    describe_fold:function (){
      if(this.click_word==='angle-down'){
        this.click_word='angle-up';
      }else {
        this.click_word='angle-down';
      }
      this.describe_show = !this.describe_show
    }
  },
  components: {
    VideoPlayer,
    Comment
  }
}
</script>

<style scoped>
.a-col-con{
  padding: 30px;
  margin-bottom: 50px;
  align-items: flex-start;
}
.b-col-con{
  align-items: flex-start;
  padding-left: 20px; padding-right: 20px
}
#video_player_box{
  align-items: center;
  justify-content: center;
  width: 100%;
  padding: 0;
  margin-top: 10px;
  margin-bottom: 10px;
}
el-tag{

}
</style>
