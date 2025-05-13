<template>
  <div>
    <h2>Login</h2>
    <form @submit.prevent="submit">
      <input v-model="username" placeholder="Username" />
      <input type="password" v-model="password" placeholder="Password" />
      <button>Sign In</button>
    </form>
    <div>No account? <router-link to="/register">Register Here</router-link></div>

  </div>
</template>

<script>
import { login } from '@/services/auth.service';
export default {
  data: () => ({ username: '', password: '' }),
  methods: {
    submit() {
      login({ username: this.username, password: this.password })
          .then(() => this.$router.push('/'))
          .catch(err => console.error(err));
    }
  }
};
</script>

