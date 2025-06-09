<script setup>
import { ref } from 'vue'
import {defineProps} from 'vue'
import {getImageUrl} from "@/utils/image";
import {usePlantStore} from "@/stores/plantStore";
import {useMonthPickerStore} from "@/stores/monthPickerStore";
import ConfirmationModal from "@/components/modals/ConfirmationModal.vue";
import DetailedPlantCardModal from "@/components/modals/DetailedPlantCardModal.vue";


const monthPickerStore = useMonthPickerStore()
const plantStore = usePlantStore()
const showConfirmModal = ref(false)

// Add this to your existing script
const showDetailedCard = ref(false)

const handleDetailClick = () => {
  showDetailedCard.value = true
}

const handleCloseDetail = () => {
  showDetailedCard.value = false
}

const handleRemoveClick = () => {
  showConfirmModal.value = true
}

const handleConfirm = () => {
  plantStore.removePlant(props.plant)
  showConfirmModal.value = false
}

const handleCancel = () => {
  showConfirmModal.value = false
}

const props = defineProps({
  plant: Object,
})

const monthsLinkLookup = {
  0: "january",
  1: "february",
  2: "march",
  3: "april",
  4: "may",
  5: "june",
  6: "july",
  7: "august",
  8: "september",
  9: "october",
  10: "november",
  11: "december"
}

const getCurrentMonthImage = () => {
  console.log(monthPickerStore.selectedMonth)
  if(!monthPickerStore.monthPickerEnabled) {
    return props.plant.imageCover
  }
  const month = monthsLinkLookup[monthPickerStore.selectedMonth]
  const imageKey = `image${month.charAt(0).toUpperCase() + month.slice(1)}`
  return props.plant[imageKey] || props.plant.imageCover
}


const monthsLookup = {
  1: "Januar",
  2: "Februar",
  3: "März",
  4: "April",
  5: "Mai",
  6: "Juni",
  7: "Juli",
  8: "August",
  9: "September",
  10: "Oktober",
  11: "November",
  12: "Dezember"
}

const getMonth = (month) => {
  return monthsLookup[month]
}

const sunlightRequirements = Number(props.plant.sunlightRequirements)

const color = '#' + props.plant.colorHex;
</script>
<template>
  <div class="h-96 w-72 rounded-xl overflow-hidden shadow-lg relative text-white group">
    <!-- Full background image -->
    <div class="absolute inset-0 overflow-hidden">
      <img
          :src="getImageUrl(getCurrentMonthImage())"
          alt="Anchusa Azurea"
          class="absolute inset-0 w-full h-full object-cover z-0 transition-transform duration-500 ease-in-out group-hover:scale-110"
      />
    </div>

    <!-- Gradient overlay -->
    <div class="absolute inset-0 z-0 transition-opacity duration-300"
         :class="{ 'opacity-0': monthPickerStore.monthPickerEnabled }"
         :style="{
           background: `linear-gradient(to bottom,
             transparent 0%,
             ${color}4D 30%,
             ${color}CC 80%
           )`
         }"
    />

    <!-- Close button -->
    <button
        v-if="$route.path === '/collections/new'"
        class="cursor-pointer absolute top-2 right-2 z-10 bg-white/30 hover:bg-white/50 text-white font-bold rounded-full w-6 h-6 flex items-center justify-center transition-opacity duration-300"
        @click="handleRemoveClick"
    >
      &times;
    </button>

    <!-- Content aligned to bottom -->
    <div class="relative z-1 flex flex-col justify-end h-full p-4">
      <!-- Bottom content -->
      <div class="space-y-3">
        <!-- Title and subtitle -->
        <div>
          <h2 class="text-xl font-bold">{{ props.plant.name }}</h2>
          <p class="text-sm transition-opacity duration-300">
            {{ props.plant.latinName }}
          </p>
        </div>

        <!-- Info tags -->
        <div v-if="!monthPickerStore.monthPickerEnabled" class="flex flex-wrap gap-2 text-sm transition-all duration-300">
          <span
              :style="{ backgroundColor: `${color}99`, color: parseInt(props.plant.colorHex, 16) > 0x7FFFFF ? '#1F1F1F' : 'white' }"
              class="px-3 py-1 rounded-full hover:opacity-75 cursor-pointer">
            {{ props.plant.heightMin + " - " + props.plant.heightMax + " cm" }}
          </span>
          <span
              :style="{ backgroundColor: `${color}99`, color: parseInt(props.plant.colorHex, 16) > 0x7FFFFF ? '#1F1F1F' : 'white' }"
              class="px-3 py-1 rounded-full hover:opacity-75 cursor-pointer">
            {{ getMonth(props.plant.bloomTimeFrom) + " - " + getMonth(props.plant.bloomTimeTo) }}
          </span>
          <span
              :style="{ backgroundColor: `${color}99`, color: parseInt(props.plant.colorHex, 16) > 0x7FFFFF ? '#1F1F1F' : 'white' }"
              class="flex px-3 py-1 rounded-full hover:opacity-75 cursor-pointer">
            <template v-for="n in sunlightRequirements" :key="n">
              <svg
                  xmlns="http://www.w3.org/2000/svg"
                  class="mr-1 h-4 w-4"
                  :style="{ color: parseInt(props.plant.colorHex, 16) > 0x7FFFFF ? '#1F1F1F' : 'white' }"
                  fill="none"
                  viewBox="0 0 16 16"
                  stroke="currentColor"
              >
                <path
                    d="M7.99999 0.666666V2M7.99999 14V15.3333M2.81332 2.81333L3.75999 3.76M12.24 12.24L13.1867 13.1867M0.666656 8H1.99999M14 8H15.3333M2.81332 13.1867L3.75999 12.24M12.24 3.76L13.1867 2.81333M11.3333 8C11.3333 9.84095 9.84094 11.3333 7.99999 11.3333C6.15904 11.3333 4.66666 9.84095 4.66666 8C4.66666 6.15905 6.15904 4.66667 7.99999 4.66667C9.84094 4.66667 11.3333 6.15905 11.3333 8Z"
                    stroke-linecap="round" stroke-linejoin="round"/>
              </svg>
            </template>
          </span>
        </div>

        <!-- Button -->
        <button 
            v-if="!monthPickerStore.monthPickerEnabled"
            @click="handleDetailClick"
            class="w-full bg-white text-black text-sm font-semibold py-2 px-4 rounded-full hover:bg-blue-100 transition-all duration-300">
          Mehr Details
        </button>
      </div>
    </div>

    <!-- Confirmation Modal -->
    <ConfirmationModal
        v-if="showConfirmModal"
        @confirm="handleConfirm"
        @cancel="handleCancel"
    />
  </div>
  
  <!-- Add this at the end of your template -->
  <DetailedPlantCardModal
      :plant="props.plant"
      :is-open="showDetailedCard"
      @close="handleCloseDetail"
  />
</template>