<script setup>
import {ref, onMounted} from 'vue';
import {useRoute} from 'vue-router';
import {getCollectionById} from '@/services/collection.service';
import PlantCard from "@/components/cards/PlantCard.vue";
import MonthScrollPicker from "@/components/slider/MonthScrollPicker.vue";
import MonthPickerToggle from "@/components/slider/MonthPickerToggle.vue";

const route = useRoute();
const collection = ref(null);
const error = ref(null);
const loading = ref(true);


onMounted(async () => {
  try {
    // const collectionId = route.params.id;
    const collectionId = route.params.collectionId.split('-').pop();
    console.log(collectionId);
    collection.value = await getCollectionById(collectionId);
  } catch (e) {
    error.value = e.message;
  } finally {
    loading.value = false;
  }
});
</script>

<template>
  <div class="p-4">
    <div v-if="loading" class="text-center">
      Loading...
    </div>

    <div v-else-if="error" class="text-red-600">
      {{ error }}
    </div>

    <div v-else-if="collection" class="mx-auto">
      <h1 class="text-2xl font-bold mb-4">{{ collection.title }}</h1>
      <p class="text-gray-600 mb-4">{{ collection.description }}</p>
      <MonthPickerToggle></MonthPickerToggle>
      <MonthScrollPicker></MonthScrollPicker>

      <div class="mt-6">
        <div class="flex flex-wrap gap-2 pr-8 pt-4">
          <div v-for="plant in collection.plants" :key="plant.id">
            <PlantCard :plant="plant"/>
          </div>
        </div>
      </div>
    </div>
    <!--    <ButtonGroup></ButtonGroup>-->
    <!--    <MonthsSlider></MonthsSlider>-->
  </div>
</template>