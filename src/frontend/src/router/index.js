import { createRouter, createWebHistory } from 'vue-router';
import { useAuthStore } from "@/stores/auth";
import LoginView from '@/views/LoginView.vue';
import RegisterView from '@/views/RegisterView.vue';
import IndexView from "@/views/IndexView.vue";



const routes = [
    {
        path: '/',
        component: () => import('@/layouts/DefaultLayout.vue'),
        meta: { requiresAuth: true},
        children: [
            { path: '', component: IndexView },
            {
                path: '/collections/:collectionId',
                component: () => import('@/views/Collections/CollectionDetail.vue')
            },
            {
                path: '/collections/new',
                component: () => import('@/views/Collections/CollectionNew.vue'),
            },
            {
                path: '/collections/:collectionId/edit',
                component: () => import('@/views/Collections/CollectionEdit.vue'),
            },
        ]
    },
    {
        path: '/',
        component: () => import('@/layouts/AuthLayout.vue'),
        children: [
            { path: 'login', component: LoginView },
            { path: '/register', component: RegisterView },
        ]
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});


router.beforeEach((to) => {
    const authStore = useAuthStore();

    const token = authStore.token;

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