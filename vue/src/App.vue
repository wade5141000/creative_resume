<template>
  <v-app>
    <v-app-bar app>
      <v-btn elevation="2" outlined class="primary--text" to="/"> Home </v-btn>
      <v-btn elevation="2" outlined class="primary--text" to="/login">
        Login
      </v-btn>
      <v-btn elevation="2" outlined class="primary--text" to="/user">
        User
      </v-btn>
      <v-btn elevation="2" outlined class="primary--text" @click="getUsers">
        getUsers
      </v-btn>
    </v-app-bar>

    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
import http from '@/utils/http';
export default {
  data() {
    return {

    };
  },
  components: {
  },
  methods: {
    getUsers(){
      http.get("/user/users")
      .then(function(response){
        console.log(response);
      })
    }
  }
};
</script>
