<template>
  <div class="app-container">
    <el-container>
      <el-header class="title"><h1>供应链优化分析平台</h1></el-header>
    </el-container>
    <el-container class="frame">
      <el-aside width="200px">
        <el-scrollbar>
          <el-menu>
            <router-link to="/home">
              <el-menu-item index="1">总览</el-menu-item>
            </router-link>
            <router-link to="/customer">
              <el-menu-item index="2">用户信息</el-menu-item>
            </router-link>
            <el-sub-menu index="3">
              <template #title> 订单信息 </template>
              <router-link to="/order">
                <el-menu-item index="3-1"> 订单信息 </el-menu-item>
              </router-link>
              <router-link to="/orderItems">
                <el-menu-item index="3-2"> 订单商品信息 </el-menu-item>
              </router-link>
            </el-sub-menu>
            <el-sub-menu index="4">
              <template #title>产品信息</template>
              <router-link to="/product">
                <el-menu-item index="4-1">产品信息</el-menu-item>
              </router-link>
              <router-link to="/category">
                <el-menu-item index="4-2">分类信息</el-menu-item>
              </router-link>
            </el-sub-menu>
            <router-link to="/department">
              <el-menu-item index="5">部门信息</el-menu-item>
            </router-link>
          </el-menu>
        </el-scrollbar>
      </el-aside>

      <el-container>
        <el-container>
          <el-header>
            <div class="childTitle">{{ childTitle }}</div>
          </el-header>
          <el-main>
            <router-view />
          </el-main>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
defineOptions({ name: 'frame' })
import { RouterLink, RouterView, useRoute } from 'vue-router'
import { ref, watch } from 'vue'

let childTitle = ref('总览')
const route = useRoute()
watch(
  () => route.meta.title,
  (newTitle) => {
    if (newTitle) {
      childTitle.value = newTitle as string
    }
  },
  { immediate: true },
)
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}
a {
  text-decoration-line: none;
}
html,
body {
  height: 100%;
  margin: 0;
  padding: 0;
}
.app-container {
  height: 100vh;
  display: flex;
  flex-direction: column;
}
.title {
  background-color: rgb(24, 102, 206);
}
.title h1 {
  margin: auto;
  color: white;
  text-shadow: 2px 3px grey;
  text-align: center;
  height: 60px;
  line-height: 60px;
}
.el-menu-item,
.el-sub-menu {
  background-color: var(--el-color-primary-light-5);
}

.frame {
  height: 100%;
}
.frame .el-aside {
  color: var(--el-text-color-primary);
  background: #cbe5ff;
}

.frame .el-menu {
  border-right: none;
}
.el-menu-item:hover {
  background-color: transparent;
}

.frame .el-main {
  padding: 0;
}
.frame .el-header {
  position: relative;
  background-color: var(--el-color-success-light-5);
  color: var(--el-text-color-primary);
}
.childTitle {
  height: 50px;
  line-height: 50px;
  font-size: 35px;
  margin-left: 50px;
  text-shadow: 2px 2px #cbe5ff;
}
.frame .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}
</style>
