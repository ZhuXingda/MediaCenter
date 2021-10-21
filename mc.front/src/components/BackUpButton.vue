<template>
  <transition name="el-fade-in-linear">
    <div id="back-up-wrapper" v-show="back_up_show">
      <button class="box-shadow c-row-con a-button"
              id="back-up-button"
              style="justify-content: center;font-weight: bold; font-size: 20px"
              @click="this.backUp">
        <i class="el-icon-top"/>
      </button>
    </div>
  </transition>
</template>

<script>
export default {
  name: "BackUpButton",
  props:{
    back_up_show:Boolean
  }
}
</script>

<style scoped>
#back-up-button{
  border-radius: 25px;
  height: 50px;
  width: 50px;
}
#back-up-wrapper{
  position: sticky;
  position: -webkit-sticky;
  top: 85%;
  margin-left: 40px;
}
</style>
