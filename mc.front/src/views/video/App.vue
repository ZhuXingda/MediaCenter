<template>
  <div class="page">
    <Header/>
    <VideoPage style="width: 100%"/>
  </div>
</template>

<script>
import Header from "../../components/Header"
import VideoPage from "../../components/VideoPage";
export default {
  name: 'App',
  components: {
    Header,
    VideoPage
  }
}
</script>
