import { defineStore } from 'pinia'
import type { Customer } from '@/types/customer.ts'
import { useCustomerAxios } from '@/utils/customerAxios.ts'

const customerAxios = useCustomerAxios()

export const useCustomerStore = defineStore('CustomerTable', {
  state: () => ({
    code: 0,
    customerData: [] as Customer[],
    pages: 0,
    total: 0,
    current: 1,
    error: null as string | null,
    customer: {} as Customer,
  }),
  actions: {
    async fetchCustomers(pageNum: number, pageSize: number, name?: string | null,segment?:string|null) {
      const res = await customerAxios.fetchCustomerPage(pageNum, pageSize,name,segment)
      console.log(res)
      const pageData = res.data ?? {
        records: [],
        total: 0,
        pages: 0,
        current: 0,
      }
      this.customerData = pageData.records
      this.pages = pageData.pages
      this.total = pageData.total
      this.current = pageData.current
      return pageData.records
    },
    async fetchCustomer(id: number) {
      try {
        const res = await customerAxios.fetchCustomer(id)
        this.customer = res.data ?? {
          id: 0,
          name: '',
          segment: '',
          isDeleted: 0,
        }
      } finally {
      }
      return this.customer
    },
    async fetchUpdateCustomer(customer: Customer) {
      try {
        const res = await customerAxios.updateCustomer(customer)
        this.code = res.code ?? 0
      } finally {
      }
      return this.code
    },
    async fetchDeleteCustomer(ids: number[]) {
      try {
        const res = await customerAxios.deleteCustomer(ids)
        this.code = res.code ?? 0
      } finally {
      }
      return this.code
    },
    setTableData(data: Customer[]) {
      this.customerData = data
    },
    getTableData() {
      return this.customerData
    },
  },
})
