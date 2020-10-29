import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        isLogin: sessionStorage.getItem('user') ? true : false,
        user: sessionStorage.getItem('user') ? JSON.parse(sessionStorage.getItem('user')) : {}
    },
    mutations: {
        login(state, user) {
            state.user = user
            state.isLogin = true
            sessionStorage.setItem("user", JSON.stringify(state.user));
        },
        logout(state){
            state.user = {}
            state.isLogin = false;
            sessionStorage.removeItem('user');
        }
    }
})


export default store;