<script setup>
import { defineEmits } from 'vue'

const emit = defineEmits(['confirm', 'cancel'])

const handleConfirm = () => {
  emit('confirm', true)
}

const handleCancel = () => {
  emit('cancel')
}
</script>

<template>
  <div class="fixed inset-0 z-50 flex items-center justify-center">
    <!-- Backdrop -->
    <div class="absolute inset-0 bg-black/50" @click="handleCancel"></div>
    
    <!-- Modal -->
    <div class="relative bg-white rounded-lg p-6 max-w-sm w-full mx-4 shadow-xl">
      <div class="text-center">
        <h3 class="text-lg font-medium text-gray-900 mb-4">
          Pflanze entfernen
        </h3>
        <p class="text-sm text-gray-500 mb-6">
          Möchten Sie diese Pflanze wirklich aus Ihrer Kollektion entfernen?
        </p>
        <div class="flex justify-center gap-4">
          <button
              @click="handleCancel"
              class="px-4 py-2 text-sm font-medium text-gray-700 bg-gray-100 rounded-md hover:bg-gray-200 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500"
          >
            Abbrechen
          </button>
          <button
              @click="handleConfirm"
              class="px-4 py-2 text-sm font-medium text-white bg-red-600 rounded-md hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500"
          >
            Entfernen
          </button>
        </div>
      </div>
    </div>
  </div>
</template>