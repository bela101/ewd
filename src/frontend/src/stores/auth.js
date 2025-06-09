import { defineStore } from 'pinia'
import { computed, ref } from 'vue'

export const useAuthStore = defineStore('auth', () => {
    const token = ref(null)

    const setToken = (newToken) => {
        token.value = newToken
    }

    const clearToken = () => {
        token.value = null
    }

    const username = computed(() => {
        if (!token.value) return null

        try {
            const payload = JSON.parse(atob(token.value.split('.')[1]))
            return payload.sub
        } catch (e) {
            return null
        }
    })

    return {
        token,
        username,
        setToken,
        clearToken,
    }


    }
)