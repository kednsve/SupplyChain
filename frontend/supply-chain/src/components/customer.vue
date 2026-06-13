<script lang="ts" setup>
import { useCustomerStore } from '@/stores/CustomerTable.ts'
import { storeToRefs } from 'pinia'
import type { ComponentSize, DialogTransition } from 'element-plus'

import { computed, onMounted, ref } from 'vue'
import type { Customer } from '@/types/customer.ts'
// 数据
defineOptions({ name: 'customer' })
const customerStore = useCustomerStore()
const { tableData, total } = storeToRefs(customerStore)

const handleSelectionChange = () => {}

onMounted(() => {
  customerStore.fetchData(currentPage.value, pageSize.value)
})
// 分页
let selectable
let currentPage = ref(1)
let pageSize = ref(12)
const background = true
const size = ref<ComponentSize>('default')
const disabled = false

const handleSizeChange = (changed: number) => {
  pageSize.value = changed
  customerStore.fetchData(currentPage.value, pageSize.value)
}
const handleCurrentChange = (changed: number) => {
  currentPage.value = changed
  customerStore.fetchData(currentPage.value, pageSize.value)
}
// 修改窗口
const dialogVisible = ref(false)
const currentAnimation = ref('fade')
const isObjectConfig = ref(false)
let customer = ref<Customer | null>(null)
const transitionConfig = computed<DialogTransition>(() => {
  if (isObjectConfig.value) {
    return {
      name: 'dialog-custom-object',
      appear: true,
      mode: 'out-in',
      duration: 500,
    }
  }
  return `dialog-${currentAnimation.value}`
})
// 修改窗口触发函数
const openDialog = (updId: number) => {
  currentAnimation.value = 'bounce'
  isObjectConfig.value = false
  dialogVisible.value = true

  customer.value = tableData.value.find((item) => item.id === updId) ?? null
}
</script>
<template>
  <el-table
    ref="multipleTableRef"
    :data="tableData"
    row-key="id"
    style="width: 100%"
    @selection-change="handleSelectionChange"
  >
    <el-table-column :selectable="selectable" type="selection" width="55" />
    <el-table-column label="Id" property="id" width="120" />
    <el-table-column label="Name" property="name" />
    <el-table-column label="Segment" property="segment" />
    <el-table-column label="operation">
      <template #default="{ row }">
        <el-button-group>
          <el-button plain type="success" @click="openDialog(row.id)"> 修改 </el-button>
          <el-button type="danger">删除</el-button>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
    v-model:current-page="currentPage"
    v-model:page-size="pageSize"
    :background="background"
    :disabled="disabled"
    :size="size"
    :total="total"
    layout="total, prev, pager, next, jumper"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />
  <!--  修改窗口-->
  <el-dialog
    v-model="dialogVisible"
    :title="`修改`"
    :transition="transitionConfig"
    class="custom-transition-dialog"
    width="30%"
  >
    <div>
      <p>message</p>
    </div>
    <template #footer>
      <el-button @click="dialogVisible = false">取消</el-button>
      <el-button type="primary" @click="dialogVisible = false"> 确认 </el-button>
    </template>
  </el-dialog>
</template>
<style lang="css" scoped>
.el-pagination {
  margin: 0 auto;
  width: 800px;
}
</style>
<!--修改窗口-->
<style>
/* Scale Animation */
.dialog-scale-enter-active,
.dialog-scale-leave-active,
.dialog-scale-enter-active .el-dialog,
.dialog-scale-leave-active .el-dialog {
  transition: all 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
}

.dialog-scale-enter-from,
.dialog-scale-leave-to {
  opacity: 0;
}

.dialog-scale-enter-from .el-dialog,
.dialog-scale-leave-to .el-dialog {
  transform: scale(0.5);
  opacity: 0;
}

/* Slide Animation */
.dialog-slide-enter-active,
.dialog-slide-leave-active,
.dialog-slide-enter-active .el-dialog,
.dialog-slide-leave-active .el-dialog {
  transition: all 0.3s cubic-bezier(0.25, 0.46, 0.45, 0.94);
}

.dialog-slide-enter-from,
.dialog-slide-leave-to {
  opacity: 0;
}

.dialog-slide-enter-from .el-dialog,
.dialog-slide-leave-to .el-dialog {
  transform: translateY(-100px);
  opacity: 0;
}

/* Bounce Animation */
.dialog-bounce-enter-active,
.dialog-bounce-leave-active,
.dialog-bounce-enter-active .el-dialog,
.dialog-bounce-leave-active .el-dialog {
  transition: all 0.5s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

.dialog-bounce-enter-from,
.dialog-bounce-leave-to {
  opacity: 0;
}

.dialog-bounce-enter-from .el-dialog,
.dialog-bounce-leave-to .el-dialog {
  transform: scale(0.3) translateY(-50px);
  opacity: 0;
}

/* Object Configuration Animation */
.dialog-custom-object-enter-active,
.dialog-custom-object-leave-active,
.dialog-custom-object-enter-active .el-dialog,
.dialog-custom-object-leave-active .el-dialog {
  transition: all 0.5s cubic-bezier(0.25, 0.8, 0.25, 1);
}

.dialog-custom-object-enter-from,
.dialog-custom-object-leave-to {
  opacity: 0;
}

.dialog-custom-object-enter-from .el-dialog,
.dialog-custom-object-leave-to .el-dialog {
  transform: rotate(180deg) scale(0.5);
  opacity: 0;
}
</style>
