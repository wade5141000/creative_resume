import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        isLogin: false,
        user: {
            jwt: "",
            id: "",
            username: ""
        }
    },
    mutations: {
        login(state, user) {
            state.user = user
            state.isLogin = true
            localStorage.setItem("user", JSON.stringify(state.user));
        },
        logout(state){
            state.user = {}
            state.isLogin = false;
            localStorage.removeItem('user');
        }
    }
})

let user = localStorage.getItem("user");
if(user){
    user = JSON.parse(user)
    store.commit('login', user)
}


export default store;