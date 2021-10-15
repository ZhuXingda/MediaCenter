<template>
  <div class="a-row-con">
    <div>
      <div class="b-col-con box-shadow" v-for="item in recommend_videos" v-on:scroll="load" >
        <el-button type="text" href='/video' style="padding: 0;margin: 0 20px 20px" @click="content_forward(8)">
          <div class="c-row-con">
            <div class="avatar-wrapper">
              <img class="avatar" src="https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto"/>
              <div class="avatar-mask"></div>
            </div>
            <div class="c-col-con" style="margin-left: 10px">
              <span class="b-font a-button" style="margin-bottom: 5px">{{ item.upLoader }}</span>
              <span class="a-font">{{item.upLoadTime}}</span>
            </div>
          </div>
        </el-button>
        <a href="http://www.baidu.com" style="text-decoration:none;">
          <div class="c-row-con" v-bind:href="video_href_build(item.id)" style="margin: 0 60px 20px;align-items: start">
            <div  style="width: 250px; height:160px; position:relative">
              <img class="img" v-bind:src="/*cover_img_src_build(item.cover)*/item.cover">
              <div class="img-mask"></div>
            </div>
            <div class="c-col-con" style="margin: 20px;align-items: start;width: 400px">
              <a class="b-font a-button">题目题目题目题目题目题目题目题目题目题目题目题目题目</a>
              <div class="a-font description" style="font-size: 14px; margin-top: 40px;width: 100%">{{ item.description }}</div>
            </div>
          </div>
        </a>
      </div>
      <div class="b-row-con b-font"
           style="justify-content:center;margin-bottom: 30px;font-size: 30px">
        <i class="el-icon-loading"></i>
      </div>
    </div>
    <transition name="el-fade-in-linear">
      <div class="back-up-wrapper" v-show="back_up_show">
        <el-button class="box-shadow c-row-con"
                   icon="el-icon-top"
                   id="back-up-button"
                   style="justify-content: center;font-weight: bold; font-size: 20px"
                   @click="back_up">
        </el-button>
      </div>
    </transition>
  </div>
</template>

<script>
import '../assets/globle.css'

const SERVICE_URL = '/video'

export default {
  name: "DisplayRow",
  data() {
    return {
      forward_index: 8,
      stop_scroll: false,
      back_up_show: false,
      recommend_videos: [
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'观察者网',
          upLoadTime: '3分钟以前',
          description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'观察者网',
          upLoadTime: '3分钟以前',
          description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'观察者网',
          upLoadTime: '3分钟以前',
          description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'观察者网',
          upLoadTime: '3分钟以前',
          description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'观察者网',
          upLoadTime: '3分钟以前',
          description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'观察者网',
          upLoadTime: '3分钟以前',
          description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
        },
        {
          id:1,
          title:'aaaaaaaaaaaaa',
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
    window.addEventListener('scroll',this.load)
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
    load(){
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
          this.recommend_videos.push(
            {
              id:1,
              title:'aaaaaaaaaaaaa',
              cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
              upLoader:'观察者网'+this.count,
              upLoadTime: '3分钟以前',
              description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
            },
            {
              id:1,
              title:'aaaaaaaaaaaaa',
              cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
              upLoader:'观察者网'+this.count,
              upLoadTime: '3分钟以前',
              description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
            },
            {
              id:1,
              title:'aaaaaaaaaaaaa',
              cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
              upLoader:'观察者网'+this.count,
              upLoadTime: '3分钟以前',
              description:'adfasdfsadfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋adfasdfasdf大厦里看风景阿萨德联发科静安寺蝶恋蜂狂骄傲斯洛伐克静安寺蝶恋蜂狂静安寺代理费喀fasdfasfasdfasdfffffffffffffffffffffffffffffffffffffffffffff什东路代付款萨拉副科级发动机打算理发空间的'
            }
          )
        },1000)
      }
    },
    back_up(){
      document.getElementById('back-up-button').blur()
      let scrollTop = document.documentElement.scrollTop;
      move_up(scrollTop)
    }
  }
}
function move_up(scrollTop){
  if(scrollTop>0){
    scrollTop=Math.max(scrollTop-500,0)
    setTimeout(()=>{
      document.documentElement.scrollTop=scrollTop
      move_up(scrollTop)
    },5)
  }
}
</script>

<style scoped>
.a-row-con{
  align-items: start;
}
.b-col-con{
  margin: 20px;
  padding: 20px;
  z-index: 300;
  align-items: start;
}
::-webkit-scrollbar {
  display: none;
}
.back-up-wrapper{
  position: sticky;
  position: -webkit-sticky;
  top: 85%;
  margin-left: 40px;
}
#back-up-button{
  border-radius: 25px;
  height: 50px;
  width: 50px;
}
</style>
