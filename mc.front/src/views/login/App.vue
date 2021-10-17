<template>
  <div class="page">
    <Header/>
    <Login/>
  </div>
</template>

<script>
import Header from "../../subpage/Header"
import Login from "../../subpage/Login";
export default {
  name: 'App',
  components: {
    Header,
    Login
  }
}
</script>
