<template>
  <el-container
    ><el-header class="title"><h1>供应链优化分析平台</h1></el-header></el-container
  >
  <el-container class="frame" style="height: 500px">
    <el-aside width="200px">
      <el-scrollbar>
        <el-menu>
          <router-link to="/home" v-on:click="changeChildTitle('总览')">
            <el-menu-item>总览</el-menu-item>
          </router-link>
          <router-link to="/customer" v-on:click="changeChildTitle('用户信息')">
            <el-menu-item>用户信息</el-menu-item>
          </router-link>
          <el-sub-menu index="1">
            <template #title> 订单信息 </template>
            <router-link to="/order" v-on:click="changeChildTitle('订单信息')">
              <el-menu-item index="1-1"> 订单信息 </el-menu-item>
            </router-link>
            <router-link to="/orderItems" v-on:click="changeChildTitle('订单商品信息')">
              <el-menu-item index="1-2"> 订单商品信息 </el-menu-item>
            </router-link>
          </el-sub-menu>
          <el-sub-menu index="2">
            <template #title>产品信息</template>
            <router-link to="/product" v-on:click="changeChildTitle('产品信息')">
              <el-menu-item index="2-1">产品信息</el-menu-item>
            </router-link>
            <router-link to="/category" v-on:click="changeChildTitle('分类信息')">
              <el-menu-item index="2-2">分类信息</el-menu-item>
            </router-link>
          </el-sub-menu>
        </el-menu>
      </el-scrollbar>
    </el-aside>

    <el-container>
      <el-container>
        <el-header>
          <div>{{ childTitle }}</div>
        </el-header>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
defineOptions({name:'frame'})
import { ElContainer, ElHeader, ElMenu, ElSubMenu } from 'element-plus'
import { RouterView, RouterLink } from 'vue-router'
import { ref } from 'vue'
let childTitle = ref()
function changeChildTitle(title: String) {
  childTitle.value = title
}
</script>

<style scoped>
.title {
  background-color: var(--el-color-primary-light-3);
}
.title h1 {
  margin: auto;
  text-shadow: 1px 2px grey;
  text-align: center;
}

.frame .el-aside {
  color: var(--el-text-color-primary);
  background: var(--el-color-primary-light-5);
}

.frame .el-menu {
  border-right: none;
}

.frame .el-main {
  padding: 0;
}
.frame .el-header {
  position: relative;
  background-color: var(--el-color-success-light-5);
  color: var(--el-text-color-primary);
}
.frame .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}
</style>
