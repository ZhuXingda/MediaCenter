<template>
  <a class="c-col-con movie-brief-wrapper" :href="movie.href">
    <div id="movie-cover">
      <img class="img" v-bind:src="/*cover_img_src_build(video.cover)*/movie.cover">
      <div class="img-mask"></div>
    </div>
    <div class="c-col-con" id="movie-title">
      <div class="a-font a-button single-line-para" style="font-weight: bold">{{ movie.title }}</div>
      <div class="a-font a-button single-line-para">{{movie.description}}</div>
    </div>
  </a>
</template>

<script>
import '../assets/globle.css'

export default {
  name: "MovieBrief",
  props:{
    movie:Object
  }
}
</script>

<style scoped>
.movie-brief-wrapper{
  width: 180px;
  height:280px;
  margin: 0 20px 20px 0;
}
#movie-cover{
  width:180px;
  height: 240px;
  position: relative
}
#movie-title{
  align-items: start;
  width: 90%;
  height: 40px;
  margin-top: 10px
}
</style>
