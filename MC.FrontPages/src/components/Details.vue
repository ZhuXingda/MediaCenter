<template>
  <div class="row-body">
    <div style="width: 68%" class=".a-col-con-no-shadow">
      <Video/>
      <Comment/>
    </div>
    <div style="width: 28%">
      <Recommend/>
    </div>
  </div>
</template>

<script>
import Recommend from "./Recommend";
import Header from "./Header";
import Video from "./Video";
import Comment from "./Comment"
import '../assets/globle.css';

export default {
  name: "Details",
  components: {
    Video,
    Header,
    Recommend,
    Comment
  }
}
</script>
<style scoped>
.row-body{
  flex-wrap:wrap
}
</style>
