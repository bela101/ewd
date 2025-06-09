<script setup>
import {ref} from 'vue'
import {logout} from "@/services/auth.service";
import router from "@/router";
import SidebarComponent from "@/components/sidebar/SidebarComponent.vue";

const logoutIndex = async () => {
  logout()
  await router.push('/login')
}

const isMobileSidebarOpen = ref(false)

const toggleMobileSidebar = () => {
  isMobileSidebarOpen.value = !isMobileSidebarOpen.value
}
</script>

<template>
  <div class="flex h-screen overflow-hidden">

<!--    &lt;!&ndash; Sidebar (Desktop) &ndash;&gt;-->
<!--    <aside class="hidden md:flex md:w-64 bg-[#FEFCF3] text-black flex-col">-->
<!--      <div class="">-->
<!--        <router-link to="/">-->
<!--          <img class="w-56 h-56" src="@/assets/logo.png" alt="Verdant Logo">-->
<!--        </router-link>-->
<!--      </div>-->
<!--      <nav class="flex-1 p-4 space-y-2 flex flex-col">-->
<!--        <div class="flex-1 space-y-2">-->
<!--          <router-link to="/" class="block px-3 py-2 rounded hover:bg-[#EFEDE5]">Öffentliche Kollektionen</router-link>-->
<!--          <router-link to="/" class="block px-3 py-2 rounded hover:bg-[#EFEDE5]">Meine Kollektionen</router-link>-->
<!--          <router-link to="/" class="block px-3 py-2 rounded hover:bg-[#EFEDE5]">Mein Account</router-link>-->
<!--        </div>-->
<!--        <div class="cursor-pointer block px-3 py-2 rounded hover:bg-[#EFEDE5]">-->
<!--          <button @click="logoutIndex">Logout</button>-->
<!--        </div>-->
<!--      </nav>-->
<!--    </aside>-->

<!--    &lt;!&ndash; Mobile Sidebar Toggle Button &ndash;&gt;-->
<!--    <div class="md:hidden absolute top-0 left-0 z-50">-->
<!--      <button @click="toggleMobileSidebar" class="p-4 text-black bg-[#FEFCF3]">-->
<!--        ☰-->
<!--      </button>-->
<!--    </div>-->

<!--    &lt;!&ndash; Sidebar (Mobile) &ndash;&gt;-->
<!--    <transition name="fade">-->
<!--      <nav-->
<!--          v-if="isMobileSidebarOpen"-->
<!--          class="fixed inset-0 z-40 bg-[#FEFCF3] text-black w-64 p-4"-->
<!--      >-->
<!--        <button @click="isMobileSidebarOpen = false" class="mb-4">✕</button>-->
<!--        <a href="#" class="block px-3 py-2 rounded hover:bg-[#EFEDE5]">Öffentliche Kollektionen</a>-->
<!--        <a href="#" class="block px-3 py-2 rounded hover:bg-[#EFEDE5]">Meine Kollektionen</a>-->
<!--        <a href="#" class="block px-3 py-2 rounded hover:bg-[#EFEDE5]">Mein Account</a>-->
<!--        <a @click="logoutIndex" href="#" class="block px-3 py-2 rounded hover:bg-[#EFEDE5]">Logout</a>-->
<!--      </nav>-->
<!--    </transition>-->

    <SidebarComponent />

    <!-- Main Content -->
    <main class="flex-1 bg-gray-100 p-6 overflow-auto">
      <router-view></router-view>
    </main>
  </div>

</template>

<style scoped>
.fade-enter-active, .fade-leave-active {
  transition: opacity .5s ease;
}

.fade-enter-from, .fade-leave-to {
  opacity: 0;
}

</style>