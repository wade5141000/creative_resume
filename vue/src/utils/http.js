import axios from "axios";
import store from '@/store';
import router from '@/router';

const instance = axios.create({
    baseURL: "http://localhost:8080/api",
    headers: {
        "Content-Type": "application/json;charset=UTF-8",
        "Cache-Control": "no-cache",
        Accept: "application/json"
    }
})

instance.interceptors.request.use((config) => {
    if(store.state.user.jwt) {
        config.headers.Authorization = store.state.user.jwt;
    }
    return config;
}, (error) => {
    return Promise.reject(error);
})

instance.interceptors.response.use((response) => {
    console.log('ajax ok');
    return response;
}, (error) => {
    if(error.response.status == 403 || error.response.status == 401) {
        console.log('需要登入');
        store.commit('logout');
        if(router.currentRoute.path.indexOf('login') === -1) {
            router.push('/login')
        }

    }
    console.log('ajax error');
    return Promise.reject(error);
})

export default instance


