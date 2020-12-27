<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="2">
        <v-text-field label="帳號" placeholder="username" v-model="username"></v-text-field>
      </v-col>
      <v-col cols="2">
        <v-text-field
          label="密碼"
          placeholder="password"
          v-model="password"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="4">
        <v-btn elevation="2" outlined class="primary--text" @click="login"> Login </v-btn>
        <v-btn elevation="2" outlined class="primary--text" @click="logout"> Logout </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from '@/utils/http';
export default {
  data(){
    return {
      username:'',
      password:''
    }
  },
  methods: {
    login() {
        // const data = {
        //   username: this.username,
        //   password: this.password
        // }
        // const formData = new FormData()
        // Object.keys(data).forEach(key => {
        //   formData.append(key, data[key])
        // })
      // http.post("/login", formData)
      //   .then((response) => {
      //     console.log('登入成功');
      //     console.log(response.data);
      //     this.$store.commit('login', response.data)
      //     let redirect = this.$route.query.redirect ? this.$route.query.redirect : '/'
      //     this.$router.push(redirect)
      //   })
      //   .catch(() => {
      //     console.log('登入失敗');
      //   })

        if(this.username === 'aaa' && this.password === 'aaa'){
          console.log('登入成功');
          this.$store.commit('login', {})
          let redirect = this.$route.query.redirect ? this.$route.query.redirect : '/'
          this.$router.push(redirect)
        }
        

    },
    logout(){
      this.$store.commit('logout')
    }
  }
}
</script>
