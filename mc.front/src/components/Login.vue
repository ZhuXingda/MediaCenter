<template>
  <div class="a-col-con box-shadow">
    <el-tabs v-model="active_name" @tab-click="tab_click" tab-position="left">
      <el-tab-pane name="log_in">
        <span slot="label"><h2>登录</h2></span>
        <form>
          <div class="b-row-con form_item">
            <span class="title">ID</span>
            <el-input v-model="login_email" clearable placeholder="请输入email" @blur="login_check_email"></el-input>
          </div>
          <div class="b-row-con form_item">
            <span class="title">密码</span>
            <el-input v-model="login_pwd" clearable show-password placeholder="请输入密码" @blur="login_check_pwd"></el-input>
          </div>
          <div class="b-row-con" style="justify-content: center">
            <img :src="check_img" style="width: 150px; height: 50px; margin-right: 20px">
            <el-button @click="check_img_refresh" :disabled="refresh_disable">{{ refresh_countdown }}</el-button>
          </div>
          <div class="b-row-con form_item">
            <span class="title">验证码</span>
            <el-input v-model="login_check" clearable placeholder="请输入验证码" @blur="login_check_check"></el-input>
          </div>
          <div class="b-row-con form_item" style="margin-top: 20px; justify-content:center;">
            <el-checkbox v-model="login_remember">七天内免登陆</el-checkbox>
          </div>
          <div class="b-row-con form_item" style="margin-top: 20px; justify-content:space-around">
            <el-button type="primary" plain @click="login_submit">登录</el-button>
            <el-button @click="login_cancel">取消</el-button>
          </div>
        </form>
      </el-tab-pane>
      <el-tab-pane name="sign_up">
        <span slot="label"><h2 class="">注册</h2></span>
        <form>
          <div class="b-row-con form_item">
            <span class="title">新ID</span>
            <el-input v-model="signup_id" clearable placeholder="请输入新账户ID" @blur="signup_check_id"></el-input>
          </div>
          <div class="b-row-con form_item">
            <span class="title">新密码</span>
            <el-input v-model="signup_pwd" clearable show-password placeholder="请输入密码" @blur="signup_check_pwd"></el-input>
          </div>
          <div class="b-row-con form_item">
            <span class="title">重复密码</span>
            <el-input v-model="signup_pwd_sec" clearable show-password placeholder="请第二次输入密码" @blur="signup_check_pwd_sec"></el-input>
          </div>
          <div class="b-row-con" style="justify-content: center">
            <img :src="check_img" style="width: 150px; height: 50px; margin-right: 20px">
            <el-button @click="check_img_refresh" :disabled="refresh_disable">{{ refresh_countdown }}</el-button>
          </div>
          <div class="b-row-con form_item">
            <span class="title">验证码</span>
            <el-input v-model="signup_check" clearable placeholder="请输入验证码" @blur="signup_check_check"></el-input>
          </div>
          <div class="b-row-con form_item" style="margin-top: 20px; justify-content:space-around">
            <el-button type="primary" plain @click="signup_submit">注册</el-button>
            <el-button @click="signup_cancel">取消</el-button>
          </div>
        </form>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import {getRequest,postRequest} from '../js/http.js'
import axios from "axios";
axios.defaults.withCredentials = true
const BASE_URL = 'http://localhost:8082/user/identity'
const CHECK_IMG_URL = '/getCheckImg'
const LOGIN_URL = '/login'
const REGISTER_URL = '/register'
export default {
  name: "Login",
  data() {
    return {
      active_name: "log_in",
      login_email:"",
      login_pwd:"",
      login_check:"",
      signup_id:"",
      signup_pwd:"",
      signup_pwd_sec:"",
      signup_check:"",
      check_img:"",
      check_id:"",
      refresh_countdown:"刷新",
      refresh_disable:false,
      refresh_time:-1,
      login_remember:false
    }
  },
  mounted() {
    this.check_img_refresh()
  },
  methods:{
    tab_click (){
      this.login_email=""
      this.login_pwd=""
      this.login_check=""
      this.signup_id=""
      this.signup_pwd=""
      this.signup_pwd_sec=""
      this.signup_check=""
    },
    login_check_email(){
      if(this.login_email.length<1){
        this.$message.error('账户ID为空');
        return false;
      }
      return true;
    },
    login_check_check(){
      if(this.login_check.length<1){
        this.$message.error('验证码为空');
        return false;
      }
      return true;
    },
    login_check_pwd(){
      if(this.login_pwd.length<1){
        this.$message.error('账户密码为空');
        return false;
      }
      return true;
    },
    login_submit() {
      if (this.login_check_email() && this.login_check_pwd() && this.login_check_check()) {
        const body = {
          email: this.login_email,
          password: this.login_pwd,
          uncheckedKey: this.login_check,
          remember: this.login_remember
        }
        postRequest(BASE_URL + LOGIN_URL, body)
          .then(res => {
            this.$notify({
              title : '登录成功',
              message : res.data.msg,
              type: 'success'
            })
            window.location.href = res.data.data
          }).catch(error=> {
          if (error.response.status===400) {
            this.$notify.error({
              title : '登录失败',
              message : error.response.data.msg
            })
          } else {
            this.$notify.error({
              title : '登录失败',
              message : '未知错误，请联系管理员'
            })
          }
        })
      }
    },
    login_cancel(){
      axios.post(BASE_URL + "/cancel",{
        withCredentials: false
      }).then(res=>{
        console.log(res)
      })
    },
    signup_check_id(){
      if(this.signup_id.length<1){
        this.$message.error('账户ID为空');
        return false;
      }
      return true;
    },
    signup_check_pwd(){
      if(this.signup_pwd.length<1){
        this.$message.error('密码为空');
        return false;
      }
      return true;
    },
    signup_check_pwd_sec(){
      if(this.signup_pwd_sec.length<1){
        this.$message.error('密码为空');
        return false;
      }
      if(this.signup_pwd!==this.signup_pwd_sec){
        this.$message.error('两次密码不一致');
        return false;
      }
      return true;
    },
    check_img_refresh(){
      this.refresh_time+=1
      axios.get(BASE_URL+CHECK_IMG_URL,  {responseType: "blob"}).then(res => {
        this.check_img = URL.createObjectURL(res.data)
      }).catch(error=>{
        this.$notify.error({
          title: '验证码加载失败',
          message: '验证码加载失败，请联系管理员'
        });
      })
      console.log(this.refresh_time)
      if(this.refresh_time>4){
        let time = 20
        let clock = window.setInterval(()=>{
          this.refresh_disable = true
          this.refresh_countdown = time + 's后重试'
          time-=1
          if (time < 0) {         //当倒计时小于0时清除定时器
            window.clearInterval(clock)
            this.refresh_countdown = '刷新'
            this.refresh_disable = false
            this.refresh_time = 0
          }
        },1000)
      }
    },
    signup_check_check(){
      if(this.signup_check.length<1){
        this.$message.error('验证码为空');
        return false;
      }
      return true;
    },
    signup_submit(){
      if(this.signup_check_id()&&this.signup_check_pwd()&&this.signup_check_pwd_sec()&&this.signup_check_check()){
        const body = {
          email: this.login_email,
          password: this.login_pwd,
          uncheckedKey: this.signup_check
        }
        postRequest(BASE_URL + REGISTER_URL, body)
          .then(res => {
            this.$notify({
              title : '注册成功',
              message : res.data.msg,
              type: 'success'
            })
            window.location.href = res.data.data
          }).catch(error=> {
          if (error.response.status===400) {
            this.$notify.error({
              title : '注册失败',
              message : error.response.data.msg
            })
          } else {
            this.$notify.error({
              title : '注册失败',
              message : '未知错误，请联系管理员'
            })
          }
        })
      }
    },
    signup_cancel(){
      this.active_name="log_in"
    }
  }
}
</script>

<style scoped>
.a-col-con{
  margin: 20px;
  padding: 40px 20px;
}
.title{
  font-size: 20px;
}
.el-input{
  width: 250px;
}
.el-tabs{
  font-size: 30px;
  width: 500px;
}
.form_item{
  margin: 30px;
}
</style>
