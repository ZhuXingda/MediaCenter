<template>
  <div class="a-col-con box-shadow">
    <dl class="b-row-con">
      <dt v-for="item in recommend_videos" style="width: 25%">
        <div class="b-col-con">
          <a class="a-font c-col-con a-button" v-bind:href="video_href_build(item.id)">
            <img style="width: 100%; height:140px" v-bind:src="cover_img_src_build(item.cover)">
            <div style="font-size: small;text-overflow: ellipsis; white-space: nowrap;  overflow: hidden">{{ item.title }}</div>
          </a>
          <a class="a-font a-button" :href="item.user_href">
            <div style="font-size: xx-small">{{ item.title }}</div>
          </a>
        </div>
      </dt>
    </dl>
    <el-divider></el-divider>
    <el-pagination v-show="nav_show()" layout="prev, pager, next, jumper" :total="page_total" :current-page="page_current" v-on:current-change="change_page"></el-pagination>
  </div>
</template>

<script>
import '../assets/globle.css'
import {getRequest, getQuery, putRequest} from '../js/http.js'

const SERVICE_URL = '/video'

export default {
  name: "DisplayTable",
  data() {
    return {
      recommend_videos: [
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },{
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },{
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },{
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },{
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto'
        }
      ],
      page_total: 1,
      page_current: 1
    }
  },
  props:{
    columnStyle: String
  },
  mounted() {
    this.init();
  },
  methods: {
    init(){
      const params = {
        pageIndex : 1,
        pageSize : 20
      };
      let URL='';
      if(this.columnStyle==='index'){
        URL = this.GLOBAL.BACK_URL + SERVICE_URL + "/recommend/index";
      }
      if(this.columnStyle==='origin'){
        URL = this.GLOBAL.BACK_URL + SERVICE_URL + "/recommend/origin";
      }
      if(this.columnStyle==='theater'){
        URL = this.GLOBAL.BACK_URL + SERVICE_URL + "/recommend/theater";
      }
      getRequest(URL,params).then(res=>{
        if(res.data.code===0){
          let data = res.data.data;
          this.page_total = (data.totalCount/20)+1;
          this.recommend_videos = data.list;
        }else {
          this.$message.error(res.data.msg);
        }
      }).catch(error=>{
        let body = error.response.data
        console.log(body)
        if(body.code === 10005){
          this.$message.error(body.msg);
          window.location.href = body.data
        }
      })
    },
    nav_show(){
      if(this.columnStyle==='index'){
        return false;
      }
      return true;
    },
    change_page(page_current){
      const URL = this.GLOBAL.BACK_URL + SERVICE_URL;
      this.page_current = page_current
      const params = {
        pageIndex : page_current,
        pageSize : 20
      }
      getRequest(URL, params).then(res=>{
        this.page_total = res.data.page_total;
        this.recommend_videos = res.data.recommend_videos;
      })
    },
    cover_img_src_build(cover){
      return this.GLOBAL.BACK_URL + SERVICE_URL + "/cover/" + cover;
    },
    video_href_build(video_id){
      return "http://www.baidu.com?id="+video_id;
    }
  }
}
</script>

<style scoped>
.a-col-con{
  margin: 20px;
  padding: 20px;
}
.b-row-con{
  margin: 0;
  justify-content: space-around;
  flex-wrap: wrap;
}
.b-col-con{
  padding: 10px;
  justify-content: space-between;
  height: 200px;
}
.c-col-con{
}
</style>
