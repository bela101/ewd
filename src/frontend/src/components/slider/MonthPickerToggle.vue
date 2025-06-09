<script setup>
import { useMonthPickerStore } from "@/stores/monthPickerStore";

const monthPickerStore = useMonthPickerStore();

</script>

<template>
  <div class="max-w-md space-y-6">
    <!-- Toggle Switch -->
    <div class="flex items-center">
      <span class="text-lg pr-3 font-medium text-gray-700 select-none">Monate simulieren</span>
      <button
          @click="monthPickerStore.toggleMonthPicker()"
          type="button"
          role="switch"
          :aria-checked="monthPickerStore.monthPickerEnabled"
          class="relative inline-flex items-center h-7 w-12 rounded-full transition-colors duration-200 ease-in-out focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500"
          :class="monthPickerStore.monthPickerEnabled ? 'bg-green-500' : 'bg-gray-200 hover:bg-gray-300'"
      >
        <span class="sr-only">Toggle months simulation</span>
        <span
            class="inline-block w-5 h-5 transform rounded-full shadow transition-transform duration-200 ease-in-out bg-white"
            :class="monthPickerStore.monthPickerEnabled ? 'translate-x-6' : 'translate-x-1'"
        ></span>
      </button>
    </div>
  </div>
</template>

<style scoped>
/* Add any additional custom styles if needed */
</style>