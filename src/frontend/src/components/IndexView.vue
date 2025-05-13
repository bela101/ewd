<script>
import { logout } from '@/services/auth.service'
import { getUsernameFromToken } from '@/services/auth.service'

export default {
  name: 'IndexView',
  data() {
    return {
      user: null,
    }
  },
  mounted() {
    this.user = getUsernameFromToken();
    console.log(this.user);
  },
  computed: {
    // simple check: token exists?
    isLoggedIn() {
      return !!localStorage.getItem('user-token')
    }
  },
  methods: {
    logout() {
      logout()                     // clear token
      this.$router.push('/login')  // send them back to login
    }
  }
}
</script>

<template>
 Welcome to the Index View {{ user || 'Guest' }}
  <button v-if="isLoggedIn" @click="logout">Logout</button>
</template>

<style scoped>

</style>