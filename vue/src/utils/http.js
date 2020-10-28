import axios from "axios";


const instance = axios.create({
    baseURL: "http://localhost:8080/my-course",
    headers: {
        "Content-Type": "application/json;charset=UTF-8",
        "Cache-Control": "no-cache",
        Accept: "application/json"
    }
})

instance.interceptors.request.use((config) => {
    let user = localStorage.getItem("user");
    if(user){
        user = JSON.parse(user)
        config.headers.Authorization = user.jwt;
    }
    return config;
}, (error) => {
    return Promise.reject(error);
})

instance.interceptors.response.use((response) => {
    console.log('ajax ok');
    return response;
}, (error) => {
    console.log('ajax error');
    return Promise.reject(error);
})

export default instance


