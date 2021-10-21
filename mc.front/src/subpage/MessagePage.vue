<template>
  <div class="a-row-con">
    <div class="b-col-con" @scroll="loadMore">
      <div class="c-row-con box-shadow" id="option-header">
        <button class="c-button" id="option" v-for="option in options"
                :class="{ 'c-chosen' : chosen_option===option.type }"
                @click="chose_option(option.type)">{{option.name}}
        </button>
      </div>
      <MessageContent :messages="msgs"/>
      <div class="c-row-con c-font loading">
        <i class="el-icon-loading"></i>
      </div>
    </div>
    <BackUpButton :back_up_show="back_up_show"/>
  </div>
</template>

<script>
import MessageContent from "../components/MessageContent";
import BackUpButton from "../components/BackUpButton";
export default {
  name: "MessagePage",
  components: {
    MessageContent,
    BackUpButton
  },
  data() {
    return {
      chosen_option:'comments_received',
      options:[
        {
          name:'收到的评论',
          type:'comments_received'
        },
        {
          name:'发出的评论',
          type:'comments_sendoff'
        },
        {
          name:'收到的点赞',
          type:'likes_received'
        },
        {
          name:'系统通知',
          type:'system_info'
        }
      ],
      msgs: [
        {
          id:1,
          content:'回复 @带带史晓雪 ：新津区第一届余波杯斯诺克比赛在自己的球房举行，非常高兴，我自己也是参赛选手，希望能够取得好成绩，同时非常感谢本土白酒品牌余波酒业的大力支持。。。在新津喝白酒就选余波！！！',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'带带张顺爬',
          upLoadTime: '3分钟以前',
          target_user: '@带带史晓雪',
          target:'笑拉了，下次回家狠狠干两杯冰淇士 笑拉了，下次回家狠狠干两杯冰淇士 笑拉了，下次回家狠狠干两杯冰淇士 笑拉了，下次回家狠狠干两杯冰淇士'
        },
        {
          id:1,
          content:'回复 @带带史晓雪 ：新津区第一届余波杯斯诺克比赛在自己的球房举行，非常高兴，我自己也是参赛选手，希望能够取得好成绩，同时非常感谢本土白酒品牌余波酒业的大力支持。。。在新津喝白酒就选余波！！！新津区第一届余波杯斯诺克比赛在自己的球房举行，非常高兴，我自己也是参赛选手，希望能够取得好成绩，同时非常感谢本土白酒品牌余波酒业的大力支持。。。在新津喝白酒就选余波！！！',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'带带张顺爬',
          upLoadTime: '3分钟以前',
          target_user: '@带带史晓雪',
          target:'我愿称之为世界第一可爱手机[开学季] ​​​\n'
        },
        {
          id:1,
          content:'回复 @带带史晓雪 ：新津区第一届余波杯斯诺克比赛在自己的球房举行，非常高兴，我自己也是参赛选手，希望能够取得好成绩，同时非常感谢本土白酒品牌余波酒业的大力支持。。。在新津喝白酒就选余波！！！',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'带带张顺爬',
          upLoadTime: '3分钟以前',
          target_user: '@带带史晓雪',
          target:'真的想把一些在实验室大呼小叫的逼男毒哑😅 ​​​'
        },
        {
          id:1,
          content:'回复 @带带史晓雪 ：新津区第一届余波杯斯诺克比赛在自己的球房举行，非常高兴，我自己也是参赛选手，希望能够取得好成绩，同时非常感谢本土白酒品牌余波酒业的大力支持。。。在新津喝白酒就选余波！！！新津区第一届余波杯斯诺克比赛在自己的球房举行，非常高兴，我自己也是参赛选手，希望能够取得好成绩，同时非常感谢本土白酒品牌余波酒业的大力支持。。。在新津喝白酒就选余波！！！',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'带带张顺爬',
          upLoadTime: '3分钟以前',
          target_user: '@带带史晓雪',
          target:'我愿称之为世界第一可爱手机[开学季] ​​​\n'
        },
        {
          id:1,
          content:'回复 @带带史晓雪 ：新津区第一届余波杯斯诺克比赛在自己的球房举行，非常高兴，我自己也是参赛选手，希望能够取得好成绩，同时非常感谢本土白酒品牌余波酒业的大力支持。。。在新津喝白酒就选余波！！！',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'带带张顺爬',
          upLoadTime: '3分钟以前',
          target_user: '@带带史晓雪',
          target:'真的想把一些在实验室大呼小叫的逼男毒哑😅 ​​​'
        },
        {
          id:1,
          content:'回复 @带带史晓雪 ：新津区第一届余波杯斯诺克比赛在自己的球房举行，非常高兴，我自己也是参赛选手，希望能够取得好成绩，同时非常感谢本土白酒品牌余波酒业的大力支持。。。在新津喝白酒就选余波！！！新津区第一届余波杯斯诺克比赛在自己的球房举行，非常高兴，我自己也是参赛选手，希望能够取得好成绩，同时非常感谢本土白酒品牌余波酒业的大力支持。。。在新津喝白酒就选余波！！！',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'带带张顺爬',
          upLoadTime: '3分钟以前',
          target_user: '@带带史晓雪',
          target:'我愿称之为世界第一可爱手机[开学季] ​​​\n'
        },
        {
          id:1,
          content:'回复 @带带史晓雪 ：新津区第一届余波杯斯诺克比赛在自己的球房举行，非常高兴，我自己也是参赛选手，希望能够取得好成绩，同时非常感谢本土白酒品牌余波酒业的大力支持。。。在新津喝白酒就选余波！！！',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'带带张顺爬',
          upLoadTime: '3分钟以前',
          target_user: '@带带史晓雪',
          target:'真的想把一些在实验室大呼小叫的逼男毒哑😅 ​​​'
        },
        {
          id:1,
          content:'回复 @带带史晓雪 ：新津区第一届余波杯斯诺克比赛在自己的球房举行，非常高兴，我自己也是参赛选手，希望能够取得好成绩，同时非常感谢本土白酒品牌余波酒业的大力支持。。。在新津喝白酒就选余波！！！新津区第一届余波杯斯诺克比赛在自己的球房举行，非常高兴，我自己也是参赛选手，希望能够取得好成绩，同时非常感谢本土白酒品牌余波酒业的大力支持。。。在新津喝白酒就选余波！！！',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'带带张顺爬',
          upLoadTime: '3分钟以前',
          target_user: '@带带史晓雪',
          target:'我愿称之为世界第一可爱手机[开学季] ​​​\n'
        },
        {
          id:1,
          content:'回复 @带带史晓雪 ：新津区第一届余波杯斯诺克比赛在自己的球房举行，非常高兴，我自己也是参赛选手，希望能够取得好成绩，同时非常感谢本土白酒品牌余波酒业的大力支持。。。在新津喝白酒就选余波！！！',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          upLoader:'带带张顺爬',
          upLoadTime: '3分钟以前',
          target_user: '@带带史晓雪',
          target:'真的想把一些在实验室大呼小叫的逼男毒哑😅 ​​​'
        }
      ],
      back_up_show: false,
    }
  },
  mounted() {
    window.addEventListener('scroll',this.loadMore)
  },
  methods:{
    chose_option(type){
      this.chosen_option = type
    },
    loadMore(){
      const scrollTop = document.documentElement.scrollTop;
      const scrollHeight = document.documentElement.scrollHeight;
      const clientHeight = document.documentElement.clientHeight;
      this.back_up_show = scrollTop > clientHeight;
      if(scrollTop+clientHeight+1>scrollHeight){
        setTimeout(() => {
          this.msgs.push(
            {
              id:1,
              content:'回复 @带带史晓雪 ：新津区第一届余波杯斯诺克比赛在自己的球房举行，非常高兴，我自己也是参赛选手，希望能够取得好成绩，同时非常感谢本土白酒品牌余波酒业的大力支持。。。在新津喝白酒就选余波！！！新津区第一届余波杯斯诺克比赛在自己的球房举行，非常高兴，我自己也是参赛选手，希望能够取得好成绩，同时非常感谢本土白酒品牌余波酒业的大力支持。。。在新津喝白酒就选余波！！！',
              cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
              upLoader:'带带张顺爬',
              upLoadTime: '3分钟以前',
              target_user: '@带带史晓雪',
              target:'我愿称之为世界第一可爱手机[开学季] ​​​\n'
            }
          )
        },1000)
      }
    },
  }
}
</script>

<style scoped>
.a-row-con{
  width: 100%;
}
.b-col-con{
  margin: 20px;
  align-items: stretch;
  width: 770px;
}
#option-header{
  padding: 5px 20px;
  justify-content: space-around;
}
#option{
  padding: 10px;
}
</style>
