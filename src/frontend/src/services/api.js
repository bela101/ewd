import axios from "axios";
import {logout} from "@/services/auth.service";

const api = axios.create({ baseURL: "http://localhost:8080/api" });

api.interceptors.request.use(cfg => {
    const token = localStorage.getItem('user-token');
    if (token) {
        cfg.headers.Authorization = `Bearer ${token}`;
    }
    return cfg;
});

api.interceptors.response.use(
    response => response,
    error => {
        if (error.response.status === 401) {
            logout();
            window.location = '/login';
        }
        return Promise.reject(error);
    }
)

export default api;