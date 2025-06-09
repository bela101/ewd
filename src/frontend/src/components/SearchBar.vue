<script setup>
import {nextTick, onMounted, onUnmounted, ref, watch} from "vue";
import {getAllPlants, searchPlants} from "@/services/plant.service";
import {debounce} from "lodash";
import {getImageUrl} from "@/utils/image";
import {useSearchStore} from "@/stores/searchStore";
import {usePlantStore} from "@/stores/plantStore";

const searchStore = useSearchStore()
const plantStore = usePlantStore()

const searchQuery = ref('')
const searchResults = ref([])
const inputRef = ref(null)
const searchBox = ref(null)

const handleSearch = debounce(async () => {
  if (searchQuery.value.length >= 2) {
    searchResults.value = await searchPlants(searchQuery.value);
  } else if (searchQuery.value.length === 0) {
    searchResults.value = await getAllPlants();
  } else {
    searchResults.value = [];
  }
}, 300)

const selectPlant = (plant) => {
  // TODO emit selected plant somewhere
  plantStore.addPlant(plant);

  searchStore.closeSearch();
  searchQuery.value = '';
  searchResults.value = [];
}

const closeSearch = () => {
  searchStore.closeSearch();
  searchQuery.value = '';
}

const handleKeydown = (e) => {
  if(e.key === 'Escape') {
    closeSearch();
  }
}

onMounted(() => {
  document.addEventListener('keydown', handleKeydown)
})

onUnmounted(() => {
  document.removeEventListener('keydown', handleKeydown)
})

watch(searchQuery, (value) => {
  searchStore.updateSearchQuery(value);
})

watch(() => searchStore.isSearchVisible, async (newValue) => {
  if (newValue && inputRef) {
    // Focus the input when search becomes visible
    nextTick(() => {
      inputRef.value.focus()
    })
    searchResults.value = await getAllPlants()
  }
})

</script>

<template>
  <div
      v-if="searchStore.isSearchVisible"
      @click.self="searchStore.closeSearch()"
      class="fixed inset-0 z-10 backdrop-blur-sm bg-black/30 flex items-start justify-center"
  >
    <div
        ref="searchBox"
        class="bg-white w-full mt-20 max-w-md px-6 py-4 rounded-2xl shadow-xl"
    >
      <input
          @input="handleSearch"
          v-model="searchQuery"
          ref="inputRef"
          type="text"
          placeholder="Search..."
          class="w-full text-lg border-none focus:outline-none"
      />
      <div v-if="searchResults.length > 0" class="mt-4 max-h-96 overflow-y-auto">
        <div v-for="plant in searchResults"
             :key="plant.id"
             class="p-2 hover:bg-gray-100 cursor-pointer rounded flex items-center"
             @click="selectPlant(plant)">
          <img v-if="plant.imageCover"
               :src="getImageUrl(plant.imageCover)"
               class="w-12 h-12 object-cover rounded mr-3"
               :alt="plant.name"/>
          <div>
            <div class="font-medium">{{ plant.name }}</div>
            <div class="text-sm text-gray-600">{{ plant.latinName }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

