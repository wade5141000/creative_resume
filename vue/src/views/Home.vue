<template>
  <v-container>
    <h1>This is home page.</h1>
    <HelloWorld :message="'wade'"/>
  </v-container>
</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue';
export default {
  components: {
    HelloWorld
  }
}
</script>
