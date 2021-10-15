<template>
  <div class="a-col-con box-shadow" style="padding: 20px 20px 0;">
    <dl class="b-col-con" style="margin: 0">
      <dt v-for="item in recommend_items">
        <a class="a-font b-row-con a-button" v-bind:href="item.url">
          <img style="width: 60%;" v-bind:src="item.img_src">
          <div class="b-col-con" style="align-items: start">
            <b>{{ item.name }}</b>
            <div>{{ item.date }}</div>
          </div>
        </a>
      </dt>
    </dl>
  </div>
</template>

<script>
import '../assets/globle.css'
import '../js/http.js'
import {getRequest, getQuery} from '../js/http.js'

export default {
  name: "Recommend",
  data() {
    return {
      recommend_items: [
        {
          url:"",
          img_src:"",
          name:"",
          date:""
        }
      ]
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
.b-row-con{
  padding-bottom: 30px;
  justify-content: space-between;
}
</style>
