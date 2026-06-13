<script lang="ts" setup>
import { RouterView } from 'vue-router'
</script>

<template>
  <div class="app-container">
    <RouterView />
  </div>
</template>

<style>
html,
body,
#app,
.app-container {
  height: 100vh;
  margin: 0;
  padding: 0;
}
a {
  text-decoration-line: none;
}
</style>
