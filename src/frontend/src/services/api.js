import axios from "axios";
import {logout} from "@/services/auth.service";
import { useAuthStore } from "@/stores/auth";

// const api = axios.create({ baseURL: `${config.BASE_URL}api/` });
const api = axios.create({ baseURL: '/api/' });


api.interceptors.request.use(cfg => {
    const authStore = useAuthStore();
    const token = authStore.token;
    // const token = localStorage.getItem('user-token');
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