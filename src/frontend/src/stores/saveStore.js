import {defineStore} from "pinia";
import {ref} from "vue";

export const useSaveStore = defineStore('saveStore', () =>{
    const isSaveModalVisible = ref(false)

    const openSaveModal = () => {
        isSaveModalVisible.value = true;
    }

    const closeSaveModal = () => {
        isSaveModalVisible.value = false;
    }

    return {
        isSaveModalVisible,
        openSaveModal,
        closeSaveModal,
    }
})