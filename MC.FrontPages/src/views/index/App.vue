<template>
  <div>
    <div>
      <Header/>
    </div>
    <Index/>
  </div>
</template>

<script>
import Header from "../../components/Header"
import Index from "../../components/Index"
export default {
  name: 'App',
  components: {
    Header,
    Index
  }
}
</script>
