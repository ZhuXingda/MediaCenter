<template>
  <div>
    <div>
      <Header/>
    </div>
    <Details/>
  </div>
</template>

<script>
import Header from "../../components/Header"
import Details from "../../components/Details";
export default {
  name: 'App',
  components: {
    Header,
    Details
  }
}
</script>
