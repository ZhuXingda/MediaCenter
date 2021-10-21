<template>
  <div class="b-col-con" id="message-wrapper">
    <div v-for="msg in messages" id="message-item" class="c-col-con box-shadow">
      <button style="padding: 0;margin: 0 20px 0">
        <div class="c-row-con">
          <div class="avatar-wrapper">
            <img class="avatar" src="https://img1.baidu.com/it/u=1853996003,3269176606&fm=26&fmt=auto"/>
            <div class="avatar-mask"></div>
          </div>
          <div class="c-col-con" style="margin-left: 10px">
            <span class="c-font a-button" style="margin-bottom: 5px">{{ msg.upLoader }}</span>
            <span class="a-font">{{msg.upLoadTime}}</span>
          </div>
        </div>
      </button>
      <div id="target" class="single-line-para">
        <a class="a-font a-button" :href="msg.target_href" style="font-size: 12px"> {{ msg.target_user }} : {{ msg.target }} </a>
      </div>
      <div id="content">
        <a class="b-font a-button" :href="msg.content_href"> {{ msg.content }} </a>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/globle.css'

export default {
  name: "MessageContent",
  props: {
    messages:Array
  }
}
</script>

<style scoped>
#message-wrapper{
  margin: 0 0 20px 0;
  padding: 0;
}
#message-item{
  padding: 20px;
  margin-top: 20px;
  align-items: start;
}
#target{
  padding: 10px 40px;
  width: 70%;
}
#content{
  padding: 0 40px;
}
</style>
