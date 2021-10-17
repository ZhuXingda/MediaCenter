<template>
  <div class="a-row-con box-shadow">
    <div v-for="video in videos" :key="video.id" class="video-brief-wrapper">
      <VideoBrief :video="video"/>
    </div>
  </div>
</template>

<script>
import '../assets/globle.css'
import VideoBrief from "../components/VideoBrief";

export default {
  name: "Index",
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
    }
  },
  methods:{
  }
}
</script>

<style scoped>
.a-row-con{
  margin: 20px;
  padding: 30px 30px 0;
  flex-wrap: wrap;
}
.video-brief-wrapper{
  width: 22%;
  height:170px;
  margin-bottom: 30px;
}
</style>
