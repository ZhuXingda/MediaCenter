<template>
  <div class="a-col-con" style="padding: 30px">
    <div class="b-col-con">
      <h1>{{ video_name }}</h1>
      <p class="a-font">{{ video_date }}</p>
    </div>
    <hr class="line">
    <div class="b-col-con" id="video_player_box">
      <VideoPlayer/>
    </div>
    <hr class="line">
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
</template>

<script>
import '../assets/globle.css'
import VideoPlayer from "./VideoPlayer"
import {getRequest, getQuery} from '../request/http.js'

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
    this.init_video()
  },
  methods:{
    init_video: function (){
      const url = 'http://localhost:8082/video'
      const params = {
        video_id : getQuery('video_id')
      }
      getRequest(url,params).then(res=>{
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
    VideoPlayer
  }
}
</script>

<style scoped>
.a-col-con{
  padding-left: 10px;
  padding-right: 10px;
  padding-bottom: 30px;
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
