<template>
  <v-footer
      color="indigo"
      app
  >
    <span class="white--text">&copy; {{ new Date().getFullYear() }}</span>
  </v-footer>
</template>

<script>
  export default {
    name: "theFooter"
  }
</script>