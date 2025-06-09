<template>
  <div class="min-h-screen bg-gradient-to-b md:bg-none from-green-100 to-white">
    <div class="min-h-screen flex flex-col md:flex-row">
      <!-- Left Side: Logo -->
      <div class="w-full md:w-1/2 md:bg-gradient-to-r md:from-green-100 md:to-white flex items-center justify-center py-8 md:py-0">
        <div class="text-center">
          <img src="../assets/image.svg" alt="Verdant Logo" class="mx-auto w-20 md:w-24 mb-4 md:mb-6" />
          <h1 class="text-4xl md:text-5xl font-medium text-green-400">Verdant</h1>
        </div>
      </div>

      <!-- Right Side: Login Form -->
      <div class="w-full md:w-1/2 flex items-center justify-center bg-white/80 md:bg-white backdrop-blur-sm md:backdrop-blur-none px-4 py-8 md:px-8 rounded-t-3xl md:rounded-none mt-8 md:mt-0">
        <div class="w-full max-w-md">
          <h1 class="text-xl md:text-2xl font-bold text-gray-900 mb-6">Einloggen</h1>

          <!-- Validation errors -->
          <div v-if="error" class="bg-red-100 text-red-700 p-3 mb-4 rounded text-sm md:text-base">
            {{ error }}
          </div>

          <form @submit.prevent="submit" class="space-y-4">
            <!-- Email -->
            <div>
              <label for="username" class="block text-sm font-medium text-gray-700 mb-1">Email</label>
              <input
                  id="username"
                  type="email"
                  inputmode="email"
                  v-model="username"
                  placeholder="Enter your Email"
                  class="w-full border border-gray-300 rounded-lg px-4 py-3 md:py-2 focus:outline-none focus:ring-2 focus:ring-blue-500 text-base bg-white"
              />
            </div>

            <!-- Password -->
            <div>
              <label for="password" class="block text-sm font-medium text-gray-700 mb-1">Passwort</label>
              <div class="relative">
                <input
                    :type="showPassword ? 'text' : 'password'"
                    id="password"
                    v-model="password"
                    placeholder="Enter your password"
                    class="w-full border border-gray-300 rounded-lg px-4 py-3 md:py-2 pr-10 focus:outline-none focus:ring-2 focus:ring-blue-500 text-base bg-white"
                />
                <button
                    type="button"
                    @click="togglePassword"
                    class="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-500 p-2"
                >
                  <component :is="showPassword ? EyeSlashIcon : EyeIcon" class="w-5 h-5" />
                </button>
              </div>
            </div>

            <!-- Submit Button -->
            <button
                type="submit"
                class="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold rounded-lg py-3 md:py-2 transition duration-200 text-base"
            >
              Einloggen
            </button>
          </form>

          <!-- Register Link -->
          <p class="text-center text-sm md:text-base text-gray-500 mt-6">
            Ich habe noch keinen Account.
            <router-link to="/register" class="text-blue-500 hover:underline ml-1">Registrieren</router-link>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { login } from '@/services/auth.service'

import {EyeIcon, EyeSlashIcon} from "@heroicons/vue/24/outline";


// Form state
const username = ref('')
const password = ref('')
const showPassword = ref(false)
const error = ref(null)

const router = useRouter()

// Password toggle
const togglePassword = () => {
  showPassword.value = !showPassword.value
}

// Simple validation helper
function isValidEmail(email) {
  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)
}

// Submit logic with validation
const submit = async () => {
  error.value = null

  if (!username.value || !password.value) {
    error.value = 'Bitte fülle alle Felder aus.'
    return
  }

  if (!isValidEmail(username.value)) {
    error.value = 'Bitte gib eine gültige E-Mail-Adresse ein.'
    return
  }

  try {
    await login({ username: username.value, password: password.value })
    router.push('/')
  } catch (err) {
    error.value = 'Login fehlgeschlagen. Bitte überprüfe deine Anmeldedaten.'
    console.error(err)
  }
}
</script>
