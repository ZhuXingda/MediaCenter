<template>
  <div class="a-col-con box-shadow">
    <div class="b-row-con">
      <MovieBrief :movie="movie" v-for="movie in movies" :key="movie.id"/>
    </div>
    <el-pagination layout="prev, pager, next, jumper" :total="page_total" :current-page="current_page" v-on:current-change="change_page"/>
  </div>
</template>

<script>
import '../assets/globle.css'
import MovieBrief from "../components/MovieBrief";

export default {
  name: "MovieTable",
  components: {MovieBrief},
  data(){
    return {
      movies: [
        {
          id:1,
          title:'观其无语-第2423期第2423期第2423期第2423期第2423期',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:''
        },
        {
          id:2,
          title:'观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网观察者网观察者网观察者网观察者网观察者网观察者网观察者网'
        },
        {
          id:3,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:''
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
        },
        {
          id:4,
          title:'aaaaaaaaaaaaa',
          cover:'https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto',
          href:'http://www.baidu.com',
          description:'观察者网'
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
  padding: 20px 0 20px 20px;
}
.b-row-con{
  width: 100%;
  flex-wrap: wrap;
}
</style>
