<template>
  <v-app id="inspire">
    <v-navigation-drawer
        v-model="drawer"
        app
    >
      <v-list dense>
        <v-list-item link to="/login">
          <v-list-item-action>
            <v-icon>mdi-home</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>登入</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link to="/user">
          <v-list-item-action>
            <v-icon>mdi-email</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>User(需要登入 帳密:aaa)</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link to="/sample">
          <v-list-item-action>
            <v-icon>mdi-voicemail</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>sample</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link to="/table">
          <v-list-item-action>
            <v-icon>mdi-table-account</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>課表管理</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar
        app
        color="indigo"
        dark
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title to="/">Application</v-toolbar-title>
    </v-app-bar>
    <v-main>
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-main>
    <the-footer></the-footer>
  </v-app>
</template>

<script>
  import theFooter from '@/components/theFooter.vue';
  export default {
    data: () => ({
      drawer: null
    }),
    components: {theFooter},
    methods: {}
  };
</script>

