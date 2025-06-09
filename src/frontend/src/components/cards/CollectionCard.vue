<template>
  <router-link class="flex flex-col w-72 transition-colors duration-200" :to="`/collections/${collectionLink}`">
    <div>
      <div class="flex relative">
        <div class="absolute left-2 bottom-2 p-1 rounded-md bg-gray-300/50 backdrop-blur-sm z-10">
          {{ plantsNumber + ' Pflanzen' || "Anzahl Pflanzen" }}
        </div>
        <div class="flex-1 h-52 rounded-l-md overflow-hidden">
          <img :src=images[0] :alt="props.plants[0]?.name || 'Pflanze1'"
               :title="props.plants[0]?.name || 'Pflanze1'"
               class="h-full w-full object-cover transition-transform duration-300 hover:scale-110"/>
        </div>
        <div class="flex-1 h-52 overflow-hidden">
          <img :src=images[1] :alt="props.plants[1]?.name || 'Pflanze2'"
               :title="props.plants[1]?.name || 'Pflanze2'"
          class="h-full w-full object-cover transition-transform duration-300 hover:scale-110"/>

        </div>
        <div class="flex-1 h-52 rounded-r-md overflow-hidden">
          <img :src=images[2] :alt="props.plants[2]?.name || 'Pflanze3'"
               :title="props.plants[2]?.name || 'Pflanze2'"
               class="h-full w-full object-cover transition-transform duration-300 hover:scale-110"/>
        </div>
      </div>
      <div class="text-lg font-semibold pt-2">{{ title || "Collection Title" }}</div>
      <p class="pt-2">{{ description || "Collection Description" }}</p>
    </div>
  </router-link>
</template>

<script setup>
import {computed, defineProps} from "vue";
import { getImageUrl } from "@/utils/image";

const props = defineProps({
  collectionId: Number,
  title: String,
  description: String,
  isPublic: Boolean,
  plants: {
    type: Array,
  },
})

const collectionLink = computed(() => props.title.replace(/[^a-zA-Z0-9]/g, '-').toLowerCase() + '-' + props.collectionId);
const plantsNumber = computed(() => props.plants?.length);
const images = computed(() => {
  return props.plants.slice(0, 3).map(plant => getImageUrl(plant.imageCover))
})

</script>