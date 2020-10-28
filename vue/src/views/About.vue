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
  </v-container>
</template>

<script>

export default {
  data(){
    return {
      username:'',
      password:''
    }
  }
}
</script>
