import { defineStore } from 'pinia'
import type { Customer } from '@/types/customer.ts'
import { useCustomerAxios } from '@/utils/customerAxios.ts'

const customerAxios = useCustomerAxios()

export const useCustomerStore = defineStore('CustomerTable', {
  state: () => ({
    code: 0,
    tableData: [] as Customer[],
    loading: false,
    pages: 0,
    total: 0,
    error: null as string | null,
    customer: {} as Customer,
  }),
  actions: {
    async fetchCustomers(pageNum: number, pageSize: number) {
      this.loading = true
      try {
        const res = await customerAxios.fetchCustomerPage(pageNum, pageSize)
        const pageData = res.data ?? {
          records: [],
          total: 0,
          pages: 0,
        }

        this.tableData = pageData.records
        this.pages = pageData.pages
        this.total = pageData.total
      } finally {
        this.loading = false
      }
    },
    async fetchCustomer(id: number) {
      try {
        this.loading = true
        const res = await customerAxios.fetchCustomer(id)
        this.customer = res.data ?? {
          id: 0,
          name: '',
          segment: '',
          isDeleted: 0,
        }
      } finally {
        this.loading = false
      }
      return this.customer
    },
    async fetchUpdateCustomer(customer: Customer) {
      try {
        this.loading = true
        const res = await customerAxios.updateCustomer(customer)
        this.code = res.code ?? 0
      } finally {
        this.loading = false
      }
      return this.code
    },
    async fetchDeleteCustomer(ids: number[]) {
      try {
        this.loading = true
        const res = await customerAxios.deleteCustomer(ids)
        this.code = res.code ?? 0
      } finally {
        this.loading = false
      }
      return this.code
    },
    setTableData(data: Customer[]) {
      this.tableData = data
    },
    getTableData() {
      return this.tableData
    },
  },
})
