<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <RouterLink to="/home">click</RouterLink>
  <RouterView />
</template>

<style>
a {
  text-decoration-line: none;
}
</style>
