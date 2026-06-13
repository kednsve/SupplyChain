<script lang="ts" setup>
import { useCustomerStore } from '@/stores/CustomerTable.ts'
import { storeToRefs } from 'pinia'
import { type ComponentSize, type DialogTransition, ElMessage } from 'element-plus'

import { computed, onMounted, ref } from 'vue'
import type { Customer } from '@/types/customer.ts'
// 数据
defineOptions({ name: 'customer' })
const customerStore = useCustomerStore()
const { tableData, total } = storeToRefs(customerStore)

const handleSelectionChange = () => {}

onMounted(() => {
  if (tableData.value.length === 0) {
    customerStore.fetchCustomers(currentPage.value, pageSize.value)
  }
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
  customerStore.fetchCustomers(currentPage.value, pageSize.value)
}
const handleCurrentChange = (changed: number) => {
  currentPage.value = changed
  customerStore.fetchCustomers(currentPage.value, pageSize.value)
}
// 窗口
const dialogVisible = ref(false)
const currentAnimation = ref('fade')
const isObjectConfig = ref(false)
let formData = ref<Record<string, any>>({})
let operation = ref('修改数据')
const isDisabled = ref(false)
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
// 窗口触发函数
const openDialog = (id: number, type: 'update' | 'delete') => {
  isDisabled.value = type === 'delete'
  operation.value = type === 'delete' ? '删除数据' : '修改数据'
  const found = tableData.value.find((item) => item.id === id)
  formData.value = { ...found }
  currentAnimation.value = 'bounce'
  isObjectConfig.value = false
  dialogVisible.value = true
}
const callExec = () => {
  if (isDisabled.value) {
    execDel()
  } else {
    execUpd()
  }
}
//修改数据
const execUpd = async () => {
  const updCustomer = formData.value as Customer
  const code = await customerStore.fetchUpdateCustomer(updCustomer)
  if (code === 200) {
    const idx = tableData.value.findIndex((item) => item.id === updCustomer.id)
    if (idx !== -1) {
      tableData.value[idx] = { ...updCustomer }
      customerStore.setTableData(tableData.value)
    }
    operationDialog('success', '更新成功')
    dialogVisible.value = false
  } else {
    operationDialog('error', '发生错误')
  }
}
// 删除数据
const execDel = async () => {
  const delCustomer = formData.value as Customer
  const code = await customerStore.fetchDeleteCustomer([delCustomer.id])
  if (code === 200) {
    await customerStore.fetchCustomers(currentPage.value, pageSize.value)
    operationDialog('success', '删除成功')
    dialogVisible.value = false
  } else {
    operationDialog('error', '发生错误')
  }
}
// 消息提示
const operationDialog = (type: 'success' | 'error', message: string) => {
  ElMessage({
    showClose: true,
    type: type,
    message: message,
  })
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
          <el-button plain type="success" @click="openDialog(row.id, 'update')"> 修改 </el-button>
          <el-button type="danger" @click="openDialog(row.id, 'delete')">删除</el-button>
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
    :title="operation"
    :transition="transitionConfig"
    class="custom-transition-dialog"
    width="30%"
  >
    <div>
      <el-form :model="formData" label-width="auto" style="max-width: 600px">
        <el-form-item label="id">
          <el-input v-model="formData!.id" disabled />
        </el-form-item>
        <el-form-item label="name">
          <el-input v-model="formData!.name" :disabled="isDisabled" />
        </el-form-item>
        <el-form-item label="segment">
          <el-select v-model="formData!.segment" :disabled="isDisabled" placeholder="segment">
            <el-option label="Consumer" value="Consumer" />
            <el-option label="Home Office" value="Home Office" />
            <el-option label="Corporate" value="Corporate" />
          </el-select>
        </el-form-item>
      </el-form>
    </div>
    <template #footer>
      <el-button @click="dialogVisible = false">取消</el-button>
      <el-button type="primary" @click="callExec"> 确认 </el-button>
    </template>
  </el-dialog>
</template>
<style lang="css" scoped>
.el-pagination {
  margin: 10px auto 0 auto;
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
