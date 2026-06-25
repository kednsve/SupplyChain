import { defineStore } from 'pinia'
import type { OrderItems } from '@/types/orderItems.ts'
import { useOrderItemsAxios } from '@/utils/orderItemsAxios.ts'

const orderItemsAxios = useOrderItemsAxios()

export const useOrderItemsStore = defineStore('OrderItemsTable', {
  state: () => ({
    code: 0,
    orderItemsData: [] as OrderItems[],
    pages: 0,
    total: 0,
    current: 1,
    error: null as string | null,
    orderItems: [] as OrderItems[],
  }),
  actions: {
    async fetchOrderItemss(
      pageNum: number,
      pageSize: number,
      productId?: number | null,
      productName?: string |null,
      quantityMin?: number | null,
      quantityMax?: number | null,
      unitPriceMin?: number | null,
      unitPriceMax?: number | null,
      totalMin?: number | null,
      totalMax?: number | null
    ) {
      const res = await orderItemsAxios.fetchOrderItemsPage(
        pageNum,
        pageSize,
        productId,
        productName,
        quantityMin,
        quantityMax,
        unitPriceMin,
        unitPriceMax,
        totalMin,
        totalMax
      )
      const pageData = res.data ?? {
        records: [],
        total: 0,
        pages: 0,
        current: 0,
      }
      this.orderItemsData = pageData.records
      this.pages = pageData.pages
      this.total = pageData.total
      this.current = pageData.current
      return pageData.records as OrderItems[]
    },
    async fetchOrderItems(id: number) {
      try {
        const res = await orderItemsAxios.fetchOrderItems(id)
        this.orderItems = res.data ?? [{
          id: 0,
          orderId: 0,
          productId: 0,
          productName: '',
          quantity: 0,
          unitPrice: 0,
          total: 0,
        }]
      } finally {
      }
      return this.orderItems
    },
    async fetchUpdateOrderItems(orderItems: OrderItems) {
      try {
        const res = await orderItemsAxios.updateOrderItems(orderItems)
        this.code = res.code ?? 0
      } finally {
      }
      return this.code
    },
    async fetchDeleteOrderItems(ids: number[]) {
      try {
        const res = await orderItemsAxios.deleteOrderItems(ids)
        this.code = res.code ?? 0
      } finally {
      }
      return this.code
    },
    setTableData(data: OrderItems[]) {
      this.orderItemsData = data
    },
    getTableData() {
      return this.orderItemsData
    },
  },
})
