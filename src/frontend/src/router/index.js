import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '@/components/LoginView.vue';
import RegisterView from '@/components/RegisterView.vue';
import IndexView from "@/components/IndexView.vue";

/* eslint-disable */



const routes = [
    { path: '/login', component: LoginView },
    { path: '/register', component: RegisterView },
    { path: '/', component: IndexView, meta: { requiresAuth: true} },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});


router.beforeEach((to) => {
    const token = localStorage.getItem('user-token');

    if (to.meta.requiresAuth && !token) {
        // trying to access a protected route without a token → redirect to login
        return { path: '/login', query: { redirect: to.fullPath } }
    }

    if ((to.path === '/login' || to.path === '/register') && token) {
        // logged in users shouldn't see login/register → redirect home
        return { path: '/' }
    }
})

export default router;