<script setup>
import {getCollections, getUserCollections} from "@/services/collection.service";
import {onMounted, ref} from "vue";
import CollectionCard from "@/components/cards/CollectionCard.vue";


const publicCollections = ref();
const userCollections = ref();

onMounted(async () => {
  publicCollections.value = await getCollections();
  userCollections.value = await getUserCollections();
})

</script>

<template>
  <h3 class="text-xl font-semibold mb-4">Öffentliche Kollektionen</h3>
  <div class="flex overflow-x-auto">
    <div class="mr-4" v-for="collection in publicCollections" :key="collection.id">
      <CollectionCard
          :collection-id="collection.id"
          :title='collection.title'
          :description="collection.description"
          :is-public="collection.isPublic"
          :plants="collection.plants"
      />
    </div>
  </div>
  <h3 class="text-xl font-semibold mb-4">Meine Kollektionen</h3>
  <div class="flex overflow-x-auto">
    <div class="mr-4" v-for="collection in userCollections" :key="collection.id">
      <CollectionCard
          :collection-id="collection.id"
          :title='collection.title'
          :description="collection.description"
          :is-public="collection.isPublic"
          :plants="collection.plants"
      />
    </div>
  </div>
</template>

<style scoped>

</style>