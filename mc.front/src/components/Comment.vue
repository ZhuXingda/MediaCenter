<template>
  <div class="a-col-con box-shadow" style="padding: 30px">
    <h1>评论区</h1>
    <el-form  class="b-row-con">
      <el-form-item label="" style="width: 80%">
        <el-input type="textarea" v-model="my_comment"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="onSubmit" type="primary" plain>评论</el-button>
      </el-form-item>
    </el-form>
    <el-divider></el-divider>
    <dl class="b-col-con" style="width:100%;">
      <dt v-for="comment in comments" class="b-row-con" style="margin: 0 10px 10px 10px">
        <div class="b-col-con" style="width: 20%">
          <el-image :src="comment.avatar" style="height: 50px; width: 50px; border-radius:50%;" fit = "contain">
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
          <p class="a-font">{{ comment.user }}</p>
        </div>
        <div class="b-col-con" style="width: 80%; height: 100px; align-items: start; justify-content: space-around">
          <div class="a-font">{{ comment.content }}</div>
          <div class="a-font">{{ comment.time }}</div>
        </div>
      </dt>
    </dl>
    <el-divider></el-divider>
    <el-pagination layout="prev, pager, next, jumper" :total="page_total" v-on:current-change="change_page"></el-pagination>
  </div>
</template>

<script>
import {getRequest, getQuery, putRequest} from '../js/http.js'
const SERVICE_GET_URL = '/getVideoComment'
const SERVICE_PUT_URL = '/createVideoComment'
const VIDEO_ID = getQuery('video_id');

export default {
  name: "Comment",
  data() {
    return {
      my_comment: '',
      page_current: 1,
      page_total: 0,
      comments: [
        {
          comment_id: '',
          user_info:{
            id:'',
            avatar: '',
            user_name: ''
          },
          content: '',
          reply_of: {
            comment_id: '',
            user_name: '',
            content: '',
          },
          time: ''
        }
      ]
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init(){
      const URL = this.BACK_URL + SERVICE_GET_URL
      const params = {
        "video_id" : VIDEO_ID,
        "page" : 1,
        "page_size" : 20
      }
      getRequest(URL, params).then(res=>{
        this.page_total = res.data.page_total;
        this.comments = res.data.comments;
      })
    },
    onSubmit(){
      const URL = this.BACK_URL + SERVICE_PUT_URL
      const params = {
        'video_id' : VIDEO_ID,
        'content' : this.my_comment
      }
      putRequest(URL,params).then(res=>{
        this.$notify({
          title: '评论成功',
          message : res.data.msg,
          type: 'success'
        });
        this.change_page(1);
      }).catch(error=>{
        if (error.response.status===400){
          this.$notify.error({
            title: '评论失败',
            message: error.response.data.msg
          });
        }else {
          this.$notify.error({
            title : '评论失败',
            message : '未知错误，请联系管理员'
          })
        }
      })
    },
    change_page(page_current){
      let params = {
        "video_id" : video_id,
        "page" : page_current,
        "page_size" : 20
      }
      getRequest(url+get_comment_url, params).then(res=>{
        this.page_total = res.page_total;
        this.comments = res.comments;
      })
    }

  }
}
</script>

<style scoped>
.a-col-con{
  padding-left: 10px;
  padding-right: 10px;
  padding-bottom: 30px;
  align-items: flex-start;
}
.b-row-con{
  width: 100%;
  justify-content: space-around;
}
.el-form-item{
  margin: 0;
}
</style>
