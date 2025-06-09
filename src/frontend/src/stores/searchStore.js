import {ref} from "vue";
import {defineStore} from "pinia";

export const useSearchStore = defineStore('modal', () => {
    const isSearchVisible = ref(false)
    const searchQuery = ref('')

    const openSearch = () => {
        isSearchVisible.value = true;
    }

    const closeSearch = () => {
        isSearchVisible.value = false;
    }

    const updateSearchQuery = (newValue) => {
        searchQuery.value = newValue;
    }

    return {
        isSearchVisible,
        openSearch,
        closeSearch,
        searchQuery,
        updateSearchQuery,
    }
})