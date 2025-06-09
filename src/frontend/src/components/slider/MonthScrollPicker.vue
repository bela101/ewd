<template>
  <div class="w-full max-w-40 relative" :class="{ 'opacity-50': !monthPickerEnabled }">
    <div
        ref="scrollContainer"
        class="h-32 overflow-y-auto scroll-smooth snap-y snap-mandatory px-3 py-1"
        :class="{ 'pointer-events-none': !monthPickerEnabled }"
        @scroll.passive="onScroll"
    >
      <div class="h-[60px]"></div>
      
      <div
          v-for="(month, index) in months"
          :key="index"
          class="snap-center flex items-center justify-center h-10 text-base transition-all duration-200 font-medium"
          :class="{
            'scale-110 font-bold text-black': index === selectedMonth,
            'text-gray-400': index !== selectedMonth,
            'cursor-pointer': monthPickerEnabled,
            'cursor-not-allowed': !monthPickerEnabled
          }"
          @click="monthPickerEnabled && selectMonth(index)"
      >
        {{ month }}
      </div>

      <div class="h-[60px]"></div>
    </div>

    <div class="rounded-md pointer-events-none absolute top-1/2 left-0 w-full h-10 transform -translate-y-1/2 border-y-2 bg-black/5"></div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useMonthPickerStore } from "@/stores/monthPickerStore";
import { storeToRefs } from "pinia";

const monthPickerStore = useMonthPickerStore();
const { monthPickerEnabled, selectedMonth } = storeToRefs(monthPickerStore);

const months = [
  'January', 'February', 'March', 'April', 'May', 'June',
  'July', 'August', 'September', 'October', 'November', 'December'
];

const scrollContainer = ref(null);
const isScrolling = ref(false);
let scrollTimeout;

onMounted(() => {
  if (selectedMonth.value === null) {
    selectedMonth.value = new Date().getMonth();
  }
  scrollToIndex(selectedMonth.value, false);
});

const itemHeight = 40;

function onScroll() {
  if (!monthPickerEnabled.value || isScrolling.value) return;
  
  clearTimeout(scrollTimeout);
  scrollTimeout = setTimeout(() => {
    const scrollTop = scrollContainer.value.scrollTop;
    const index = Math.round((scrollTop - itemHeight) / itemHeight);

    if (index >= 0 && index < months.length && index !== selectedMonth.value) {
      selectedMonth.value = index;
    }
  }, 50);
}

function scrollToIndex(index, smooth = true) {
  if (!scrollContainer.value) return;
  
  isScrolling.value = true;
  if (!smooth) {
    scrollContainer.value.style.scrollBehavior = 'auto';
  }
  
  scrollContainer.value.scrollTop = (index * itemHeight) + itemHeight;
  
  if (!smooth) {
    // Force a reflow
    scrollContainer.value.offsetHeight;
    scrollContainer.value.style.scrollBehavior = 'smooth';
  }
  
  setTimeout(() => {
    isScrolling.value = false;
  }, 100);
}

function selectMonth(index) {
  if (!monthPickerEnabled.value) return;
  selectedMonth.value = index;
  scrollToIndex(index);
}
</script>

<style scoped>
/* Hide scrollbar but maintain functionality */
.overflow-y-auto {
  scrollbar-width: none;
  -ms-overflow-style: none;
}

.overflow-y-auto::-webkit-scrollbar {
  display: none;
}

/* Add momentum scrolling for iOS */
@supports (-webkit-overflow-scrolling: touch) {
  .overflow-y-auto {
    -webkit-overflow-scrolling: touch;
  }
}
</style>