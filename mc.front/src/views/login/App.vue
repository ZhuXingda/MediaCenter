<template>
  <div class="page">
    <Header/>
    <Login/>
  </div>
</template>

<script>
import Header from "../../components/Header"
import Login from "../../components/Login";
export default {
  name: 'App',
  components: {
    Header,
    Login
  }
}
</script>
