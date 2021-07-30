<template>
  <div class="col-body">
    <div>
      <IndexRecommend/>
    </div>
    <div style="width: 100%; margin-top: 50px">
      <Comment/>
    </div>
  </div>
</template>

<script>
import Comment from "./Comment";
import IndexRecommend from "./IndexRecommend";

export default {
  name: "Index",
  components: {Comment, IndexRecommend}
}
</script>

<style scoped>

</style>
