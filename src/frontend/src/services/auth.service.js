import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth/';

export function register(user) {
    return axios.post(API_URL + 'register', user);
}

export function login(user) {
    return axios.post(API_URL + 'login', user)
        .then(resp => {
            localStorage.setItem('user-token', resp.data.token);
            return resp;
        });
}

export function logout() {
    localStorage.removeItem('user-token');
}

export function getUsernameFromToken() {
    const token = localStorage.getItem('user-token');
    if ( !token ) return null;
    try {
    //     JWT format: header.payload.signature
        const payload = JSON.parse(atob(token.split('.')[1]));
        return payload.sub;
    } catch (err) {
        return null;
    }
}