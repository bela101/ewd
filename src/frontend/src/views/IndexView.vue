<script setup>
import CollectionList from "@/components/CollectionList.vue";
import {onMounted, ref} from 'vue'
import {useAuthStore} from "@/stores/auth";
import AddButton from "@/components/buttons/AddButton.vue";
import router from "@/router";
import {getUserId, getUsername} from "@/services/user.service";

const user = ref(null)
const userId = ref(null)
const username = ref(null)

const authStore = useAuthStore()

onMounted(async () => {
  user.value = await authStore.username;
  userId.value = await getUserId();
  username.value = await getUsername();
})


</script>

<template>
  <h2 class="text-2xl font-semibold mb-4">Willkommen bei Verdant {{ username }}</h2>
  <AddButton @click="router.push('collections/new')" class="ml-auto" text="Kollektion erstellen"></AddButton>

  <CollectionList/>

</template>

<style scoped>
.fade-enter-active, .fade-leave-active {
  transition: opacity .5s ease;
}

.fade-enter-from, .fade-leave-to {
  opacity: 0;
}

</style>