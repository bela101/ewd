<template>
  <button
      class="flex items-center justify-center px-4 py-2 text-black text-lg font-medium bg-green-300 rounded-full hover:bg-green-400 transition-colors duration-200 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-opacity-50">
    <svg
        class="w-6 h-6 mr-2"
        viewBox="0 0 24 24"
        fill="none"
        stroke="currentColor"
        stroke-width="2"
        stroke-linecap="round"
        stroke-linejoin="round"
    >
      <line x1="12" y1="5" x2="12" y2="19"></line>
      <line x1="5" y1="12" x2="19" y2="12"></line>
    </svg>
    <span class="">{{text}}</span>
  </button>
</template>
<script setup>
import { defineProps } from 'vue'

defineProps({
  text: String,
})

</script>