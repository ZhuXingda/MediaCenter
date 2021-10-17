<template>
  <a style="width: 100%;height: 100%;text-decoration:none" class="c-col-con" :href="video.href">
    <div style="width:100%;height: 100%;position: relative">
      <img class="img" v-bind:src="/*cover_img_src_build(video.cover)*/video.cover">
      <div class="img-mask"></div>
    </div>
    <div class="c-row-con" style="width: 100%;margin-top: 10px">
      <div class="b-avatar-wrapper">
        <img class="avatar" src="https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto"/>
        <div class="avatar-mask"></div>
      </div>
      <div class="c-col-con" style="align-items: start;width: 80%">
        <div class="a-font a-button single-line-para" style="margin-bottom: 5px">{{ video.title }}</div>
        <div class="a-font a-button single-line-para">{{video.uploader}}</div>
      </div>
    </div>
  </a>
</template>

<script>
import '../assets/globle.css'
export default {
  name: "VideoBrief",
  props:{
    video:Object
  }
}
</script>

<style scoped>

</style>
