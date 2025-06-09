<template>
  <div class="w-full max-w-3xl mx-auto px-4 py-6">
    <!-- Slider -->
    <input
        type="range"
        min="0"
        max="11"
        v-model="currentMonthIndex"
        class="w-full h-4 appearance-none rounded-full outline-none"
        :class="trackColorClass"
    />

    <!-- Labels -->
    <div class="flex justify-between text-xs mt-2 text-gray-600">
      <span
          v-for="(month, index) in months"
          :key="month"
          :class="index === currentMonthIndex ? 'font-bold text-black' : ''"
          class="w-1/12 text-center"
      >
        {{ month }}
      </span>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';

const months = [
  'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
  'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
];

const currentMonthIndex = ref(new Date().getMonth());

const trackColorClass = computed(() => {
  const month = currentMonthIndex.value;
  if ([2, 3, 4].includes(month)) return 'bg-green-400';   // Spring
  if ([5, 6, 7].includes(month)) return 'bg-red-500';     // Summer
  if ([8, 9, 10].includes(month)) return 'bg-orange-400'; // Fall
  return 'bg-blue-500';                                   // Winter
});
</script>

<style scoped>
input[type='range']::-webkit-slider-thumb {
  -webkit-appearance: none;
  appearance: none;
  width: 1.5rem;
  height: 1.5rem;
  background-color: #86efac; /* Tailwind green-300 */
  border-radius: 9999px;
  border: 2px solid white;
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
  cursor: pointer;
  margin-top: -0.5rem; /* to vertically center */
}

input[type='range']::-moz-range-thumb {
  width: 1.5rem;
  height: 1.5rem;
  background-color: #86efac;
  border-radius: 9999px;
  border: 2px solid white;
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
  cursor: pointer;
}
</style>
