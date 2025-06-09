<script setup>
import { ref } from 'vue'
import { logout } from "@/services/auth.service"
import router from "@/router"

const emit = defineEmits(['sidebarClose'])

const isMobileSidebarOpen = ref(false)

const toggleMobileSidebar = () => {
  isMobileSidebarOpen.value = !isMobileSidebarOpen.value
  emit('sidebarClose')
}

const logoutUser = async () => {
  logout()
  await router.push('/login')
}

const navigationItems = [
  { name: 'Öffentliche Kollektionen', route: '/' },
  { name: 'Meine Kollektionen', route: '/' },
  { name: 'Mein Account', route: '/' },
]
</script>

<template>
  <div>
    <!-- Desktop Sidebar -->
    <aside class="hidden md:flex md:w-64 bg-[#FEFCF3] text-black flex-col h-screen">
      <div class="flex justify-center py-4">
        <router-link to="/">
          <img class="w-40 h-40 object-contain" src="@/assets/logo.png" alt="Verdant Logo">
        </router-link>
      </div>
      
      <nav class="flex-1 p-4 flex flex-col">
        <div class="flex-1 space-y-2">
          <router-link 
            v-for="item in navigationItems" 
            :key="item.name"
            :to="item.route"
            class="block px-4 py-2.5 rounded-lg transition-colors duration-200 hover:bg-[#EFEDE5] active:bg-[#E8E5D8]"
            :class="{ 'bg-[#EFEDE5]': $route.path === item.route }"
          >
            {{ item.name }}
          </router-link>
        </div>
        <div class="pt-4 border-t border-[#EFEDE5]">
          <button 
            @click="logoutUser"
            class="w-full text-left px-4 py-2.5 rounded-lg transition-colors duration-200 hover:bg-[#EFEDE5] active:bg-[#E8E5D8]"
          >
            Logout
          </button>
        </div>
      </nav>
    </aside>

    <!-- Mobile Toggle Button -->
    <div class="md:hidden fixed top-4 left-4 z-50">
      <button 
        @click="toggleMobileSidebar"
        class="p-3 rounded-lg bg-[#FEFCF3] shadow-lg hover:bg-[#EFEDE5] active:bg-[#E8E5D8] transition-colors duration-200"
      >
        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" />
        </svg>
      </button>
    </div>

    <!-- Mobile Sidebar -->
    <transition
      enter-active-class="transition-transform duration-300 ease-out"
      enter-from-class="-translate-x-full"
      enter-to-class="translate-x-0"
      leave-active-class="transition-transform duration-300 ease-in"
      leave-from-class="translate-x-0"
      leave-to-class="-translate-x-full"
    >
      <nav
        v-if="isMobileSidebarOpen"
        class="fixed inset-0 z-40 w-64 bg-[#FEFCF3] shadow-xl"
      >
        <div class="flex flex-col h-full">
          <div class="flex justify-center items-center p-4">
            <router-link to="/">
              <img class="w-32 h-32 object-contain" src="@/assets/logo.png" alt="Verdant Logo">
            </router-link>
          </div>

          <div class="flex-1 px-4 py-2 space-y-2">
            <router-link 
              v-for="item in navigationItems" 
              :key="item.name"
              :to="item.route"
              class="block px-4 py-2.5 rounded-lg transition-colors duration-200 hover:bg-[#EFEDE5] active:bg-[#E8E5D8]"
              :class="{ 'bg-[#EFEDE5]': $route.path === item.route }"
              @click="toggleMobileSidebar"
            >
              {{ item.name }}
            </router-link>
          </div>

          <div class="p-4 border-t border-[#EFEDE5]">
            <button 
              @click="logoutUser"
              class="w-full text-left px-4 py-2.5 rounded-lg transition-colors duration-200 hover:bg-[#EFEDE5] active:bg-[#E8E5D8]"
            >
              Logout
            </button>
          </div>
        </div>
      </nav>
    </transition>

    <!-- Mobile Backdrop -->
    <transition
      enter-active-class="transition-opacity duration-300 ease-out"
      enter-from-class="opacity-0"
      enter-to-class="opacity-100"
      leave-active-class="transition-opacity duration-300 ease-in"
      leave-from-class="opacity-100"
      leave-to-class="opacity-0"
    >
      <div
        v-if="isMobileSidebarOpen"
        class="fixed inset-0 z-30 bg-black bg-opacity-50 md:hidden"
        @click="toggleMobileSidebar"
      ></div>
    </transition>
  </div>
</template>

<style scoped>
.router-link-active {
  @apply bg-[#EFEDE5];
}
</style>