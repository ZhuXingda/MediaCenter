<template>
  <div class="row-body">
    <Video style="width: 70%"/>
    <Recommend style="width: 20%"/>
  </div>
</template>

<script>
import Recommend from "./Recommend";
import Video from "./Video";
import '../assets/globle.css';

export default {
  name: "VideoPage",
  components: {
    Video,
    Recommend
  }
}
</script>
<style scoped>
</style>
