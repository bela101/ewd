import axios from 'axios';
import  { useAuthStore } from "@/stores/auth";

// const API_URL = `${config.BASE_URL}api/auth/`;

const API_URL = '/api/auth/'


export function register(user) {
    return axios.post(API_URL + 'register', null, {
        headers: {
            'Authorization': 'Basic ' + btoa(user.username + ':' + user.password)
        }
    });
}

export function login(user) {
    return axios.post(API_URL + 'login', null, {
        headers: {
            'Authorization': 'Basic ' + btoa(user.username + ':' + user.password),
        }
    })
    .then(resp => {
        const authStore = useAuthStore();
        authStore.setToken(resp.data.token);
        return resp;
    });
}

export function logout() {
    const authStore = useAuthStore();
    authStore.clearToken();
}

export function getUsernameFromToken() {
    const authStore = useAuthStore();
    return authStore.username;
}
