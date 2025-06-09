<script setup>
import { defineProps, defineEmits, ref, computed } from 'vue'
import { getImageUrl } from "@/utils/image"

const props = defineProps({
  plant: {
    type: Object,
    required: true
  },
  isOpen: {
    type: Boolean,
    required: true
  }
})

const emit = defineEmits(['close'])
const currentImageIndex = ref(0)
const touchStartX = ref(0)
const touchEndX = ref(0)
const minSwipeDistance = 50 // minimum distance for a swipe

const handleTouchStart = (event) => {
  touchStartX.value = event.touches[0].clientX
}

const handleTouchMove = (event) => {
  touchEndX.value = event.touches[0].clientX
}

const handleTouchEnd = () => {
  const swipeDistance = touchEndX.value - touchStartX.value

  if (Math.abs(swipeDistance) > minSwipeDistance) {
    if (swipeDistance > 0) {
      previousImage() // Swipe right
    } else {
      nextImage() // Swipe left
    }
  }

  // Reset values
  touchStartX.value = 0
  touchEndX.value = 0
}



// Get all available images from the plant
const plantImages = computed(() => {
  const images = []
  const months = ['Cover', 'January', 'February', 'March', 'April', 'May', 'June',
    'July', 'August', 'September', 'October', 'November', 'December']

  months.forEach(month => {
    const imageKey = month === 'Cover' ? 'imageCover' : `image${month}`
    if (props.plant[imageKey]) {
      images.push({
        url: props.plant[imageKey],
        month: month === 'Cover' ? 'Cover Photo' : month
      })
    }
  })
  return images
})

const nextImage = () => {
  currentImageIndex.value = (currentImageIndex.value + 1) % plantImages.value.length
}

const previousImage = () => {
  currentImageIndex.value = currentImageIndex.value === 0
      ? plantImages.value.length - 1
      : currentImageIndex.value - 1
}


const closeModal = () => {
  emit('close')
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
</script>

<template>
  <div v-if="isOpen"
       class="fixed inset-0 bg-black bg-opacity-50 z-40 flex items-center justify-center p-4">
    <div class="bg-white rounded-xl max-w-4xl w-full max-h-[90vh] overflow-y-auto relative">
      <!-- Close button -->
      <button
          @click="closeModal"
          class="absolute z-50 top-4 right-4 text-gray-500 hover:text-gray-700">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
        </svg>
      </button>

      <!-- Content -->
      <div class="p-6">
        <div class="flex flex-col md:flex-row gap-6">
          <!-- Image carousel section -->
          <div class="md:w-1/2 relative">
            <div class="relative h-96 rounded-lg overflow-hidden group"
                 @touchstart="handleTouchStart"
                 @touchmove="handleTouchMove"
                 @touchend="handleTouchEnd">
              <!-- Current image with transition -->
              <img
                  :src="getImageUrl(plantImages[currentImageIndex].url)"
                  :alt="`${plant.name} - ${plantImages[currentImageIndex].month}`"
                  class="w-full h-full object-cover transition-transform duration-300"
              />

              <!-- Navigation arrows - visible on desktop -->
              <button
                  @click="previousImage"
                  class="hidden md:block absolute left-2 top-1/2 -translate-y-1/2 bg-white/80 hover:bg-white text-gray-800 rounded-full p-2 opacity-0 group-hover:opacity-100 transition-opacity">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
                </svg>
              </button>

              <button
                  @click="nextImage"
                  class="hidden md:block absolute right-2 top-1/2 -translate-y-1/2 bg-white/80 hover:bg-white text-gray-800 rounded-full p-2 opacity-0 group-hover:opacity-100 transition-opacity">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
                </svg>
              </button>

              <!-- Swipe indicator - only visible on mobile -->
              <div class="md:hidden absolute inset-x-0 bottom-16 flex justify-center text-white text-sm">
                <div class="bg-black/50 px-3 py-1 rounded-full">
                  Swipe to navigate
                </div>
              </div>

              <!-- Image counter and month indicator -->
              <div class="absolute bottom-4 left-1/2 -translate-x-1/2 bg-black/50 text-white px-3 py-1 rounded-full text-sm">
                {{ plantImages[currentImageIndex].month }} ({{ currentImageIndex + 1 }}/{{ plantImages.length }})
              </div>
            </div>

            <!-- Thumbnail preview - hidden on mobile -->
            <div class="hidden md:flex mt-4 gap-2 overflow-x-auto pb-2">
              <button
                  v-for="(image, index) in plantImages"
                  :key="index"
                  @click="currentImageIndex = index"
                  class="flex-shrink-0 w-16 h-16 rounded-lg overflow-hidden"
                  :class="{ 'ring-2 ring-blue-500': currentImageIndex === index }">
                <img
                    :src="getImageUrl(image.url)"
                    :alt="`${plant.name} - ${image.month} thumbnail`"
                    class="w-full h-full object-cover"
                />
              </button>
            </div>
          </div>



          <!-- Details section -->
          <div class="md:w-1/2">
            <h2 class="text-3xl font-bold text-gray-900">{{ props.plant.name }}</h2>
            <p class="text-xl text-gray-600 italic mb-4">{{ props.plant.latinName }}</p>

            <!-- Props.Plant details -->
            <div class="space-y-4">
              <div>
                <h3 class="font-semibold text-gray-900">Height</h3>
                <p>{{ props.plant.heightMin }} - {{ props.plant.heightMax }} cm</p>
              </div>

              <div>
                <h3 class="font-semibold text-gray-900">Bloom Time</h3>
                <p>{{ getMonth(props.plant.bloomTimeFrom) }} - {{ getMonth(props.plant.bloomTimeTo) }}</p>
              </div>

              <div>
                <h3 class="font-semibold text-gray-900">Sunlight Requirements</h3>
                <div class="flex items-center">
                  <template v-for="n in Number(props.plant.sunlightRequirements)" :key="n">
                    <svg xmlns="http://www.w3.org/2000/svg"
                         class="h-5 w-5 text-yellow-500"
                         viewBox="0 0 16 16">
                      <path d="M7.99999 0.666666V2M7.99999 14V15.3333M2.81332 2.81333L3.75999 3.76M12.24 12.24L13.1867 13.1867M0.666656 8H1.99999M14 8H15.3333M2.81332 13.1867L3.75999 12.24M12.24 3.76L13.1867 2.81333M11.3333 8C11.3333 9.84095 9.84094 11.3333 7.99999 11.3333C6.15904 11.3333 4.66666 9.84095 4.66666 8C4.66666 6.15905 6.15904 4.66667 7.99999 4.66667C9.84094 4.66667 11.3333 6.15905 11.3333 8Z"/>
                    </svg>
                  </template>
                </div>
              </div>

              <!-- Additional props.plant details can be added here -->
              <div v-if="props.plant.description">
                <h3 class="font-semibold text-gray-900">Description</h3>
                <p class="text-gray-700">{{ props.plant.description }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>