
<template>
  <div style="height: 100vh; width: 100%;">
    <Index />
  </div>
</template>

<script setup lang="ts" name="App">
import Index from './components/index.vue'
</script>

<style>
/* 全局样式 */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html, body {
  height: 100%;
  width: 100%;
  overflow: hidden;
}

#app {
  height: 100%;
  width: 100%;
}
</style>
