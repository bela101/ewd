import {defineStore} from "pinia";
import {ref} from "vue";

export const usePlantStore = defineStore('plantStore', () =>{
    const selectedPlants = ref([])

    const addPlant = (plant) => {
        selectedPlants.value.push(plant)
    }

    const removePlant = (plant) => {
        selectedPlants.value.splice(selectedPlants.value.indexOf(plant), 1)
    }

    const removeAllPlants = () => {
        selectedPlants.value.splice(0, selectedPlants.value.length)
    }

    return {
        selectedPlants,
        addPlant,
        removePlant,
        removeAllPlants,
    }
})