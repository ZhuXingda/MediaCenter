<template>
  <div class="a-col-con">
    <dl class="b-row-con">
      <dt v-for="item in recommend_items">
        <a class="a b-col-con a-button" v-bind:href="item.url">
          <!--        <img style="width: 60%;" v-bind:src="item.img_src">-->
          <img style="width: 100%" src="../assets/toby2.jpeg">
          <b>{{ item.name }}</b>
        </a>
      </dt>
    </dl>
    <el-pagination layout="prev, pager, next, jumper" :total="page_total" v-on:current-change="changePage"></el-pagination>
  </div>
</template>

<script>
import '../assets/globle.css'
import '../request/http.js'
import {getRequest, getQuery} from '../request/http.js'

export default {
  name: "IndexRecommend",
  data() {
    return {
      recommend_items: [
        {
          url:"",
          img_src:"",
          name:"",
          date:""
        }
      ],
      page_total: 200,

    }
  },
  mounted() {
    this.init_recommend();
  },
  methods: {
    init_recommend(){
      const url = 'http://localhost:8082/recommend';
      const params = {
        type : "relative_recommend",
        video_id : getQuery('video_id')
      };
      getRequest(url,params).then(res=>{
        this.recommend_items = res.items;
      })
    }
  }
}
</script>

<style scoped>
.a-col-con{
 padding-bottom: 50px;
}
.b-row-con{
  margin: 0;
  justify-content: space-around;
  padding: 50px 20px 20px 50px;
  flex-wrap: wrap;
}
.b-col-con{
  margin: 0 40px 40px 0;
  width: 300px;
  height: 280px;
  justify-content: space-between;
}
</style>
