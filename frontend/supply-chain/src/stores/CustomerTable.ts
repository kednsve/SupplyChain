import { defineStore } from 'pinia'
import axios from 'axios'
import type { Customer } from '@/types/customer.ts'

export const useCustomerStore = defineStore('CustomerTable', {
  state: () => ({
    tableData: [] as Customer[],
    loading: false,
    pages: 0,
    total: 0,
    error: null as string | null,
  }),
  actions: {
    async fetchData(pageNum: number, pageSize: number) {
      this.loading = true
      try {
        const res = await axios.post('http://localhost:8080/api/customer', {
          page: pageNum,
          pageSize: pageSize,
        })
        this.tableData = res.data.data.records
        this.pages = res.data.data.pages
        this.total = res.data.data.total
      } finally {
        this.loading = false
      }
    },
  },
})
