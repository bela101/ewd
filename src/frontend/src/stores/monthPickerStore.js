import {defineStore} from "pinia";
import {ref} from "vue";

export const useMonthPickerStore = defineStore('monthsPickerStore', () =>{

    const selectedMonth = ref(null)

    const monthPickerEnabled = ref(false)

    const toggleMonthPicker = () => {
        monthPickerEnabled.value = !monthPickerEnabled.value;
    }

    return {
        selectedMonth,
        monthPickerEnabled,
        toggleMonthPicker,
    }
})