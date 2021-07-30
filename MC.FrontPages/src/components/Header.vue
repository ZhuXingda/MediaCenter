<template>
  <div class="a-row-con" style="padding: 10px 40px;">
    <div class="b-row-con" style="height: 40px; width: 200px;">
      <a :href="index_link" class="a a-button" style="font-size: 24px">
        <b>(゜-゜)つロ 干杯</b>
      </a>
    </div>
    <a :href="header_link" class="b-row-con a a-button" style="width: 130px">
      <el-image :src="user_avatar" style="height: 50px; width: 50px; border-radius:50%;" fit = "contain">
        <div slot="error" class="image-slot">
          <i class="el-icon-picture-outline"></i>
        </div>
      </el-image>
      <h4>{{ header_describe }}</h4>
    </a>
  </div>
</template>
<script type="text/javascript">
import '../assets/globle.css';
import {getRequest} from '../request/http.js'

const url = 'http://localhost:8082/login/header'
export default {
  name: 'Header',
  data() {
    return {
      user_avatar: '',
      header_link: '',
      header_describe: '',
      index_link: window.location.path+"/index"
    }
  },
  mounted() {
    console.log(url);
    getRequest(url).then(res=>{
      this.user_avatar = res.user_avatar;
      this.header_link = res.header_link;
      this.header_describe = res.header_describe;
    })
  }
}
</script>

<style>

</style>
