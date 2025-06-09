<template>
  <div
      v-if="saveStore.isSaveModalVisible"
      @click.self="saveStore.closeSaveModal()"
      class="fixed inset-0 z-50 backdrop-blur-sm bg-black/30 flex items-center justify-center"
  >
    <div class="bg-white w-full max-w-lg p-6 rounded-2xl shadow-2xl space-y-4">
      <h2 class="text-2xl font-semibold">Save Collection</h2>

      <div class="space-y-2">
        <label class="block text-sm font-medium">
          Title
          <span class="text-red-600">*</span>
        </label>
        <input
            v-model="form.title"
            type="text"
            :class="[
              'w-full border rounded-md p-2',
              { 'border-red-500': showErrors && !form.title }
            ]"
            placeholder="Enter collection title"
        />
        <span v-if="showErrors && !form.title" class="text-red-500 text-sm">
          Title is required
        </span>
      </div>

      <div class="space-y-2">
        <label class="block text-sm font-medium">
          Description
          <span class="text-red-600">*</span>
        </label>
        <textarea
            v-model="form.description"
            rows="3"
            :class="[
              'w-full border rounded-md p-2',
              { 'border-red-500': showErrors && !form.description }
            ]"
            placeholder="Enter collection description"
        ></textarea>
        <span v-if="showErrors && !form.description" class="text-red-500 text-sm">
          Description is required
        </span>
      </div>

      <div class="flex items-center space-x-2">
        <input id="public" type="checkbox" v-model="form.isPublic" />
        <label for="public" class="text-sm">Make collection public</label>
      </div>

      <div class="flex justify-end space-x-3">
        <button
            @click="saveStore.closeSaveModal()"
            class="px-4 py-2 border border-gray-300 text-gray-700 rounded-md hover:bg-gray-50"
        >
          Cancel
        </button>
        <button
            @click="save"
            class="px-4 py-2 bg-green-600 text-white rounded-md hover:bg-green-700 disabled:bg-green-300"
            :disabled="isSubmitting"
        >
          {{ isSubmitting ? 'Saving...' : 'Save' }}
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useSaveStore } from '@/stores/saveStore'
import {createCollection} from "@/services/collection.service";
import {usePlantStore} from "@/stores/plantStore";
import {getUserId} from "@/services/user.service";
import router from "@/router";

const saveStore = useSaveStore()
const showErrors = ref(false)
const isSubmitting = ref(false)

const form = ref({
  title: '',
  description: '',
  isPublic: false,
})

function isFormValid() {
  return form.value.title.trim() !== '' && form.value.description.trim() !== ''
}

async function save() {
  showErrors.value = true
  
  if (!isFormValid()) {
    return
  }
  
  try {
    isSubmitting.value = true
    
    // Trim whitespace from inputs
    const formData = {
      title: form.value.title.trim(),
      description: form.value.description.trim(),
      isPublic: form.value.isPublic
    }
    
    console.log('Saving collection:', formData)
    // Add your API call here
    await createCollection(
        {
          "title": formData.title,
          "description": formData.description,
          "isPublic": form.value.isPublic,
          "user": {
            "id": await getUserId()
          },
          "plants": [
            ...usePlantStore().selectedPlants.map(plant => ({ id: plant.id })),
          ],
    });

    // Reset form
    form.value = {
      title: '',
      description: '',
      isPublic: false
    }
    showErrors.value = false
    
    usePlantStore().removeAllPlants()
    saveStore.closeSaveModal()
  } catch (error) {
    console.error('Error saving collection:', error)
    // You might want to show an error message to the user here
  } finally {
    isSubmitting.value = false
    await router.push('/')
  }
}
</script>

<style scoped>
/* Optional: Add custom styles for the required field asterisk */
.required::after {
  content: '*';
  color: #dc2626;
  margin-left: 4px;
}
</style>