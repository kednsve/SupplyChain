<script lang="ts" setup>
defineOptions({ name: 'orderItems' })
import { useOrderItemsStore } from '@/stores/OrderItemsTable.ts'
import { storeToRefs } from 'pinia'
import { type ComponentSize, ElButton, ElButtonGroup, ElMessage } from 'element-plus'

import { onMounted, ref } from 'vue'
import type { OrderItems } from '@/types/orderItems.ts'
import { InfoFilled } from '@element-plus/icons-vue'
// 数据
const orderItemsStore = useOrderItemsStore()
const { orderItemsData, total, current } = storeToRefs(orderItemsStore)
const loading = ref(true)

let tableData = ref(orderItemsData.value)
onMounted(async () => {
  if (tableData.value.length === 0) {
    if (orderItemsData.value.length === 0) {
      tableData.value = await orderItemsStore.fetchOrderItemss(current.value, pageSize.value)
    }
    loading.value = false
  } else {
    loading.value = false
  }
})
// 分页
let pageSize = ref(10)
let usePage = ref(true)
const size = ref<ComponentSize>('default')

const handleSizeChange = async (changed: number) => {
  current.value = 1
  pageSize.value = changed
  tableData.value = await orderItemsStore.fetchOrderItemss(current.value, pageSize.value)
}
const handleCurrentChange = async (changed: number) => {
  current.value = changed
  tableData.value = await orderItemsStore.fetchOrderItemss(current.value, pageSize.value)
}
// 搜索
let searchId = ref('')
let cancelShow = ref(false)
const searchById = async () => {
  if (!/^\d+$/.test(searchId.value)) {
    operationDialog('error', '请输入正确的数字id')
    return
  }
  current.value = 1
  usePage.value = false
  cancelShow.value = true
  tableData.value = await orderItemsStore.fetchOrderItems(Number(searchId.value))
}
const searchCancel = async () => {
  current.value = 1
  tableData.value = orderItemsStore.getTableData()
  usePage.value = true
  searchId.value = ''
  cancelShow.value = false
}
// 高级搜索
let searchProductId = ref<string>('')
let searchProductName = ref<string>('')
let searchQuantityMin = ref<string>('')
let searchQuantityMax = ref<string>('')
let searchUnitPriceMin = ref<string>('')
let searchUnitPriceMax = ref<string>('')
let searchTotalMin = ref<string>('')
let searchTotalMax = ref<string>('')

const BSReset = async () => {
  searchProductId.value = ''
  searchProductName.value = ''
  searchQuantityMin.value = ''
  searchQuantityMax.value = ''
  searchUnitPriceMin.value = ''
  searchUnitPriceMax.value = ''
  searchTotalMin.value = ''
  searchTotalMax.value = ''
  tableData.value = await orderItemsStore.fetchOrderItemss(1, pageSize.value)
}

const BSCommit = async () => {
  loading.value = true
  tableData.value = await orderItemsStore.fetchOrderItemss(
    1,
    pageSize.value,
    Number(searchProductId.value),
    searchProductName.value,
    Number(searchQuantityMin.value),
    Number(searchQuantityMax.value),
    Number(searchUnitPriceMin.value),
    Number(searchUnitPriceMax.value),
    Number(searchTotalMin.value),
    Number(searchTotalMax.value)
  )
  loading.value = false
}
// 批量删除
const clicked = ref(false)
function onCancel() {
  clicked.value = true
}
let selectList = ref<number[]>([])
const handleSelectionChange = (val: OrderItems[]) => {
  selectList.value = val.map((orderItems) => orderItems.id)
}
const mutiDel = async () => {
  if (selectList.value.length === 0) {
    operationDialog('error', '请勾选要删除的信息')
  } else {
    const code = await orderItemsStore.fetchDeleteOrderItems(selectList.value)
    if (code === 200) {
      await orderItemsStore.fetchOrderItemss(current.value, pageSize.value)
      tableData.value = orderItemsStore.getTableData()
      operationDialog('success', '删除成功')
    } else {
      operationDialog('error', '删除失败')
    }
  }
  clicked.value = false
}
// 窗口
const dialogVisible = ref(false)
const currentAnimation = ref('fade')
let formData = ref<Record<string, any>>({})
let operation = ref('修改数据')
const isDisabled = ref(false)
// 窗口触发函数
const openDialog = (id: number, type: 'update' | 'delete') => {
  isDisabled.value = type === 'delete'
  operation.value = type === 'delete' ? '删除数据' : '修改数据'
  const found = tableData.value.find((item) => item.id === id)
  formData.value = { ...found }
  currentAnimation.value = 'bounce'
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
  const updOrderItems = formData.value as OrderItems
  const code = await orderItemsStore.fetchUpdateOrderItems(updOrderItems)
  if (code === 200) {
    const idx = orderItemsData.value.findIndex((item) => item.id === updOrderItems.id)
    if (idx !== -1) {
      orderItemsData.value[idx] = { ...updOrderItems }
      orderItemsStore.setTableData(orderItemsData.value)
    }
    operationDialog('success', '更新成功')
    dialogVisible.value = false
  } else {
    operationDialog('error', '发生错误')
  }
}
// 删除数据
const execDel = async () => {
  const delOrderItems = formData.value as OrderItems
  const code = await orderItemsStore.fetchDeleteOrderItems([delOrderItems.id])
  if (code === 200) {
    tableData.value = await orderItemsStore.fetchOrderItemss(current.value, pageSize.value)
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
  <!--  搜索与批量删除 -->
  <div class="topline">
    <div class="searchMore">
      <el-collapse expand-icon-position="left">
        <el-collapse-item title="高级">
          <el-form class="betterSearch">
            <el-form-item>
              <el-input v-model="searchProductId" placeholder="ProductId" style="width: 120px" />
            </el-form-item>
            <el-form-item>
              <el-input v-model="searchProductName" placeholder="ProductName" style="width: 120px" />
            </el-form-item>
            <el-form-item>
              <el-input v-model="searchUnitPriceMin" placeholder="UnitPriceMin" style="width: 120px" />
            </el-form-item>
            <el-form-item>
              <el-input v-model="searchUnitPriceMax" placeholder="UnitPriceMax" style="width: 120px" />
            </el-form-item>
            <el-form-item>
              <el-input v-model="searchTotalMin" placeholder="TotalMin" style="width: 120px" />
            </el-form-item>
            <el-form-item>
              <el-input v-model="searchTotalMax" placeholder="TotalMax" style="width: 120px" />
            </el-form-item>
            <el-button-group>
              <el-button type="info" @click="BSReset">重置</el-button>
              <el-button type="primary" @click="BSCommit">搜索</el-button>
            </el-button-group>
          </el-form>
        </el-collapse-item>
      </el-collapse>
    </div>
    <div class="topRight">
      <div class="searchById">
        <el-input v-model="searchId" placeholder="orderId" style="width: 150px; height: 32px" />
        <el-button-group style="display: flex; flex-direction: row">
          <el-button style="margin-left: 10px" type="primary" @click="searchById">搜索</el-button>
          <el-button v-show="cancelShow" type="info" @click="searchCancel">取消</el-button>
        </el-button-group>
      </div>
      <div class="multiDel">
        <el-popconfirm
          :icon="InfoFilled"
          :title="`确认删除id为{` + selectList + `}的信息？`"
          icon-color="#FF0000"
          width="220"
          @cancel="onCancel"
        >
          <template #reference>
            <el-button type="danger">批量删除</el-button>
          </template>
          <template #actions="{ cancel }">
            <el-button size="small" type="primary" @click="cancel">取消</el-button>
            <el-button size="small" type="danger" @click="mutiDel"> 确定 </el-button>
          </template>
        </el-popconfirm>
      </div>
    </div>
  </div>
  <!--  数据表-->
  <el-table
    ref="multipleTableRef"
    v-loading="loading"
    :data="tableData"
    row-key="id"
    style="width: 100%; height: 525px"
    @selection-change="handleSelectionChange"
  >
    <el-table-column type="selection" width="55" />
    <el-table-column align="center" label="Id" property="id" width="80" />
    <el-table-column align="center" label="OrderId" property="orderId" width="80" />
    <el-table-column align="center" label="ProductId" property="productId" width="100" />
    <el-table-column align="center" label="ProductName" property="productName" width="400" />
    <el-table-column align="center" label="Quantity" property="quantity" width="100" />
    <el-table-column align="center" label="UnitPrice" property="unitPrice" width="100" />
    <el-table-column align="center" label="Total" property="total" width="200" />
    <el-table-column align="center" label="operation" width="150">
      <template #default="{ row }">
        <el-button-group>
          <el-button plain type="success" @click="openDialog(row.id, 'update')"> 修改 </el-button>
          <el-button type="danger" @click="openDialog(row.id, 'delete')">删除</el-button>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
    v-show="usePage"
    v-model:current-page="current"
    v-model:page-size="pageSize"
    :background="true"
    :page-sizes="[5, 10, 50, 100]"
    :size="size"
    :total="total"
    layout="total, sizes, prev, pager, next, jumper"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />
  <!--  修改窗口-->
  <el-dialog
    v-model="dialogVisible"
    :title="operation"
    :transition="`dialog-${currentAnimation}`"
    class="custom-transition-dialog"
    width="30%"
  >
    <div>
      <el-form :model="formData" label-width="auto" style="max-width: 600px">
        <el-form-item label="id">
          <el-input v-model="formData!.id" disabled />
        </el-form-item>
        <el-form-item label="orderId">
          <el-input v-model="formData!.orderId" disabled />
        </el-form-item>
        <el-form-item label="productId">
          <el-input v-model="formData!.productId" disabled />
        </el-form-item>
        <el-form-item label="productName">
          <el-input v-model="formData!.productName" :disabled="isDisabled" />
        </el-form-item>
        <el-form-item label="quantity">
          <el-input v-model="formData!.quantity" :disabled="isDisabled" />
        </el-form-item>
        <el-form-item label="unitPrice">
          <el-input v-model="formData!.unitPrice" :disabled="isDisabled" />
        </el-form-item>
        <el-form-item label="total">
          <el-input v-model="formData!.total" disabled />
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
.topline {
  margin-left: 30px;
  margin-top: 10px;
  width: 100%;
  display: flex;
}
.searchMore {
  width: 600px;
}
.betterSearch .el-form-item {
  margin-right: 10px;
}
.betterSearch {
  width: 100%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
.searchById {
  display: flex;
  flex-direction: row;
  width: 50%;
}
.topRight {
  display: flex;
  flex-direction: row;
  margin-left: 10%;
  width: 40%;
}
</style>
<!--修改窗口-->
<style scoped>
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
