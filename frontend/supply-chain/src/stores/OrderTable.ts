import { defineStore } from 'pinia'
import type { Order } from '@/types/order.ts'
import { useOrderAxios } from '@/utils/orderAxios.ts'

const orderAxios = useOrderAxios()

export const useOrderStore = defineStore('OrderTable', {
  state: () => ({
    code: 0,
    orderData: [] as Order[],
    pages: 0,
    total: 0,
    current: 1,
    error: null as string | null,
    order: {} as Order,
  }),
  actions: {
    async fetchOrders(
      pageNum: number,
      pageSize: number,
      start?: string | null,
      end?: string | null,
      customerId?: number | null,
      salesMin?: string | null,
      salesMax?: string | null,
      status?: string | null,
      region?: string | null,
      country?: string | null,
      city?: string | null,
    ) {
      const res = await orderAxios.fetchOrderPage(
        pageNum,
        pageSize,
        start,
        end,
        customerId,
        salesMin,
        salesMax,
        status,
        region,
        country,
        city,
      )
      const pageData = res.data ?? {
        records: [],
        total: 0,
        pages: 0,
        current: 0,
      }
      this.orderData = pageData.records
      this.pages = pageData.pages
      this.total = pageData.total
      this.current = pageData.current
      return pageData.records
    },
    async fetchOrder(id: number) {
      try {
        const res = await orderAxios.fetchOrder(id)
        this.order = res.data ?? {
          id: 0,
          date: '',
          customerId: 0,
          sales: '0',
          status: '',
          region: '',
          country: '',
          city: '',
          isDeleted: 0,
        }
      } finally {
      }
      return this.order
    },
    async fetchUpdateOrder(order: Order) {
      try {
        const res = await orderAxios.updateOrder(order)
        this.code = res.code ?? 0
      } finally {
      }
      return this.code
    },
    async fetchDeleteOrder(ids: number[]) {
      try {
        const res = await orderAxios.deleteOrder(ids)
        this.code = res.code ?? 0
      } finally {
      }
      return this.code
    },
    setTableData(data: Order[]) {
      this.orderData = data
    },
    getTableData() {
      return this.orderData
    },
  },
})
