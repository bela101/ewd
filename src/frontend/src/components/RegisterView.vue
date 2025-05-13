<template>
  <div>
    <h2>Register</h2>
    <form @submit.prevent="submit">
      <div v-if="error" class="error-message">{{ error }}</div>
      <input v-model="username" placeholder="Username" />
      <input type="password" v-model="password" placeholder="Password" />
      <button>Sign Up</button>
    </form>
    <div>I am already registered <router-link to="/login">Login Here</router-link></div>

  </div>
</template>

<script>
import { register } from '@/services/auth.service';
export default {
  data: () => ({ username: '', password: '', error: null }),
  methods: {
    submit() {
      register({ username: this.username, password: this.password })
          .then(() => this.$router.push('/login'))
          .catch(err => {
            console.error(err);
            this.error = err;
          });
    }
  }
};
</script>