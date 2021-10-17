<template>
  <div class="page">
    <Header/>
    <VideoPage style="width: 100%"/>
  </div>
</template>

<script>
import Header from "../../subpage/Header"
import VideoPage from "../../subpage/VideoPage";
export default {
  name: 'App',
  components: {
    Header,
    VideoPage
  }
}
</script>
