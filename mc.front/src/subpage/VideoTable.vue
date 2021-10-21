<template>
  <div class="a-col-con box-shadow">
    <div class="b-row-con">
      <div v-for="video in videos" :key="video.id" class="video-brief-wrapper">
        <VideoBrief :video="video"/>
      </div>
    </div>
    <el-pagination layout="prev, pager, next, jumper" :total="page_total" :current-page="current_page" v-on:current-change="change_page"/>
  </div>
</template>

<script>
import VideoBrief from "../components/VideoBrief";
import '../assets/globle.css'

export default {
  name: "VideoTable",
  components: {VideoBrief},
  data(){
    return {
      videos: [
        {
          id:1,
          title:'观其无语-第2423期第2423期第2423期第2423期第2423期',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:2,
          title:'观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网'
        },
        {
          id:3,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          uploader:'观察者网'
        }
      ],
      page_total: 10,
      current_page: 1,
    }
  },
  methods:{
  }
}
</script>

<style scoped>
.a-col-con{
  margin: 20px;
  padding: 30px;
}
.b-row-con{
  width: 100%;
  flex-wrap: wrap;
}
.video-brief-wrapper{
  width: 22%;
  height:170px;
  margin-bottom: 30px;
}
</style>
