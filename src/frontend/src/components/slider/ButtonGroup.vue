<template>
  <div class="w-max-72 mx-auto py-6 overflow-auto">
    <div class="flex">
      <button
          v-for="(month, index) in months"
          :key="index"
          @click="currentMonthIndex = index"
          class="mr-1 px-3 py-2 rounded-md text-white font-medium transition-colors duration-200"
          :class="[
          currentMonthIndex === index ? 'ring-2 ring-offset-1 ring-gray-500' : '',
          monthColor(index)
        ]"
      >
        {{ month }}
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const months = [
  'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
  'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
];

const currentMonthIndex = ref(new Date().getMonth());

function monthColor(index) {
  if ([2, 3, 4].includes(index)) return 'bg-green-500 hover:bg-green-600';   // Spring
  if ([5, 6, 7].includes(index)) return 'bg-red-500 hover:bg-red-600';       // Summer
  if ([8, 9, 10].includes(index)) return 'bg-orange-500 hover:bg-orange-600'; // Fall
  return 'bg-blue-500 hover:bg-blue-600';                                    // Winter
}
</script>
