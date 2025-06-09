<script setup>
import AddButton from "@/components/buttons/AddButton.vue";
import SaveButton from "@/components/buttons/SaveButton.vue";
import SearchBar from "@/components/SearchBar.vue";
import {useSearchStore} from "@/stores/searchStore";
import {useSaveStore} from "@/stores/saveStore";
import SaveCollectionModal from "@/components/modals/SaveCollectionModal.vue";
import {usePlantStore} from "@/stores/plantStore";
import {storeToRefs} from "pinia";
import PlantCard from "@/components/cards/PlantCard.vue";

const searchStore = useSearchStore()
const saveStore = useSaveStore()
const plantStore = usePlantStore()

const {selectedPlants} = storeToRefs(plantStore)

</script>

<template>
  <div class="flex">
    <AddButton @click="searchStore.openSearch()" class="mr-auto" text="Pflanze hinzufügen"/>
    <SaveButton @click="saveStore.openSaveModal()" class="ml-auto" text="Kollektion speichern"></SaveButton>

    <SearchBar/>

    <SaveCollectionModal/>
  </div>
  <div class="flex flex-wrap gap-2 pr-8 pt-4">
    <div v-for="plant in selectedPlants" :key="plant.id">
      <PlantCard :plant="plant"></PlantCard>
    </div>
  </div>


</template>