<script lang="ts" setup>
import { ElConfigProvider } from 'element-plus';
import { zhCn } from 'element-plus/es/locales.mjs';
import { RouterView } from 'vue-router'
</script>

<template>
  <ElConfigProvider class="app-container" :locale="zhCn">
    <RouterView />
  </ElConfigProvider>
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
