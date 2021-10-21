<template>
  <div class="a-row-con">
    <div style="width: 810px">
      <div class="b-col-con box-shadow" v-for="update in updates" @scroll="loadMore">
        <button style="padding: 0;margin: 0 20px 20px" @click="content_forward(8)">
          <div class="c-row-con">
            <div class="avatar-wrapper">
              <img class="avatar" src="https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto"/>
              <div class="avatar-mask"></div>
            </div>
            <div class="c-col-con" style="margin-left: 10px">
              <span class="c-font a-button" style="margin-bottom: 5px">{{ update.upLoader }}</span>
              <span class="a-font">{{update.upLoadTime}}</span>
            </div>
          </div>
        </button>
        <a href="http://www.baidu.com" :href="video_href_build(update.id)" class="c-row-con" id="update_content">
          <div id="img-wrapper">
            <img class="img" v-bind:src="/*cover_img_src_build(update.cover)*/update.cover">
            <div class="img-mask"></div>
          </div>
          <div class="c-col-con" id="info-wrapper">
            <a class="c-font a-button">{{ update.title }}</a>
            <div class="a-font description" style="margin-top: 20px; font-size: 14px">{{ update.description }}</div>
          </div>
        </a>
      </div>
      <div class="b-row-con c-font loading">
        <i class="el-icon-loading"></i>
      </div>
    </div>
    <BackUpButton :back_up_show="back_up_show"/>
  </div>
</template>

<script>
import '../assets/globle.css'
import BackUpButton from "../components/BackUpButton";

const SERVICE_URL = '/video'

export default {
  name: "Subscription",
  components: {BackUpButton},
  data() {
    return {
      forward_index: 8,
      stop_scroll: false,
      back_up_show: false,
      updates: [
        {
          id:1,
          title:'观其由于第2313期-擦防晒邓弗里斯达芙妮考虑到可能发案例',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'观察者网',
          upLoadTime: '3分钟以前',
          description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景'
        },
        {
          id:1,
          title:'观其由于第2313期-擦防晒邓',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'观察者网',
          upLoadTime: '3分钟以前',
          description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
        },
        {
          id:1,
          title:'观其由于第2313期',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'观察者网',
          upLoadTime: '3分钟以前',
          description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
        },
        {
          id:1,
          title:'观其由于第2313期-擦防晒邓弗里斯达芙妮考虑到可能发案例',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'观察者网',
          upLoadTime: '3分钟以前',
          description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
        },
        {
          id:1,
          title:'观其由于第2313期-擦防晒邓弗里斯达芙妮考虑到可能发案例',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'观察者网',
          upLoadTime: '3分钟以前',
          description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
        },
        {
          id:1,
          title:'观其由于第2313期-擦防晒邓弗里斯达芙妮考虑到可能发案例',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'观察者网',
          upLoadTime: '3分钟以前',
          description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
        },
        {
          id:1,
          title:'观其由于第2313期-擦防晒邓弗里斯达芙妮考虑到可能发案例',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'观察者网',
          upLoadTime: '3分钟以前',
          description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
        }
      ],
      page_total: 1,
      page_current: 1,
      count: 0
    }
  },
  mounted() {
    window.addEventListener('scroll',this.loadMore)
  },
  methods:{
    content_forward(index){
      this.forward_index = index
      this.$emit('content_forward', this.forward_index)
    },
    cover_img_src_build(cover){
      return this.GLOBAL.BACK_URL + SERVICE_URL + "/cover/" + cover;
    },
    video_href_build(video_id){
      return "http://www.baidu.com?id="+video_id;
    },
    loadMore(){
      const scrollTop = document.documentElement.scrollTop;
      const scrollHeight = document.documentElement.scrollHeight;
      const clientHeight = document.documentElement.clientHeight;
      if(scrollTop>clientHeight){
        this.back_up_show=true
      }else {
        this.back_up_show=false
      }
      if(scrollTop+clientHeight+1>scrollHeight){
        setTimeout(() => {
          this.count += 1;
          this.updates.push(
            {
              id:1,
              title:'观其由于第2313期-擦防晒邓弗里斯达芙妮考虑到可能发案例',
              cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
              upLoader:'观察者网'+this.count,
              upLoadTime: '3分钟以前',
              description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
            },
            {
              id:1,
              title:'观其由于第2313期-擦防晒邓弗里斯达芙妮考虑到可能发案例',
              cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
              upLoader:'观察者网'+this.count,
              upLoadTime: '3分钟以前',
              description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
            },
            {
              id:1,
              title:'观其由于第2313期-擦防晒邓弗里斯达芙妮考虑到可能发案例',
              cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
              upLoader:'观察者网'+this.count,
              upLoadTime: '3分钟以前',
              description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
            }
          )
        },1000)
      }
    }
  }
}
</script>

<style scoped>
.a-row-con{
  align-items: start;
}
.b-col-con{
  margin: 20px;
  padding: 30px;
  z-index: 300;
  align-items: start;
}
#update_content{
  justify-content: space-between;
  padding: 0 40px;
  height:140px;
}
#img-wrapper{
  width: 250px;
  position:relative;
  height: 100%
}
#info-wrapper{
  margin: 20px;
  align-items: start;
  justify-content: start;
  width: 65%;
  height: 100%
}
</style>
