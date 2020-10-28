<template>
  <v-app>
    <v-app-bar app>
      
      <v-btn elevation="2" outlined class="primary--text" to="/"> 註冊 </v-btn>
      <v-btn elevation="2" outlined class="primary--text" to="/about">
        登入
      </v-btn>
    </v-app-bar>

    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
export default {
  data() {
    return {

    };
  },
  components: {
  },
  
};
</script>
