<template>

</template>

<script>
import solid from '@fortawesome/fontawesome-free-solid'
import regular from '@fortawesome/fontawesome-free-regular'
import brands from '@fortawesome/fontawesome-free-brands'
import fontawesome from "@fortawesome/fontawesome";
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

fontawesome.library.add(solid)
fontawesome.library.add(regular)
fontawesome.library.add(brands)

const FRONT_SERVER_HOST = 'localhost'
const FRONT_SERVER_PORT = 8080
const FRONT_URL = 'http://' + FRONT_SERVER_HOST + ":" + FRONT_SERVER_PORT
const BACK_SERVER_HOST = 'localhost'
const BACK_SERVER_PORT = 8082
const BACK_URL = 'http://' + BACK_SERVER_HOST + ":" + BACK_SERVER_PORT
export default {
  name: "Common",
  FontAwesomeIcon,
  FRONT_SERVER_HOST,
  FRONT_SERVER_PORT,
  FRONT_URL,
  BACK_SERVER_HOST,
  BACK_SERVER_PORT,
  BACK_URL
}
</script>

<style scoped>

</style>
