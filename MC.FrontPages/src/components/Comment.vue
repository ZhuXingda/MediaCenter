<template>
  <div class="a-col-con" style="padding: 30px">
    <h1>评论区</h1>
    <hr class="line">
    <el-form  class="b-row-con">
      <el-form-item label="" style="width: 80%">
        <el-input type="textarea" v-model="my_comment"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="onSubmit" type="primary" plain>评论</el-button>
      </el-form-item>
    </el-form>
    <hr class="line">
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
    <hr class="line">
    <el-pagination layout="prev, pager, next, jumper" :total="page_total" v-on:current-change="changePage"></el-pagination>
  </div>
</template>

<script>
import {getRequest, getQuery} from '../request/http.js'
const url = 'http://localhost:8082/comment'
const get_comment_url = '/getVideoComment'
const create_comment_url = '/createVideoComment'
const video_id = getQuery('video_id')

export default {
  name: "Comment",
  data() {
    return {
      my_comment: '',
      page_current: 1,
      page_total: 0,
      comments: [
        {
          id:"",
          avatar: "",
          user: "",
          content: "",
          time: ""
        }
      ]
    }
  },
  mounted() {
    this.init_comment();
  },
  methods: {
    init_comment(){
      let params = {
        "video_id" : video_id,
        "page" : 1,
        "page_size" : 20
      }
      getRequest(url+get_comment_url, params).then(res=>{
        console.log(res);
        this.page_total = res.page_total;
        this.comments = res.comments;
      })
    },
    onSubmit(){
      let params = {
        'video_id' : video_id,
        'content' : this.my_comment
      }
      getRequest(url+create_comment_url,params).then(res=>{
        console.log(res);
        if(res.code==0){
          this.$notify({
            title: '成功',
            message : '评论成功',
            type: 'success'
          });
          this.changePage(1);
        }else {
          this.$notify.error({
            title: '错误',
            message: '评论失败 '
          });
        }
      }).catch(()=>{
        this.$notify.error({
          title: '错误',
          message: '评论失败'
        });
      })
    },
    changePage(page_current){
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
