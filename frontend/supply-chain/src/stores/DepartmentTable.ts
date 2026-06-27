import { defineStore } from 'pinia'
import type { Department } from '@/types/department.ts'
import { useDepartmentAxios } from '@/utils/departmentAxios.ts'

const departmentAxios = useDepartmentAxios()

export const useDepartmentStore = defineStore('DepartmentTable', {
  state: () => ({
    code: 0,
    departmentData: [] as Department[],
    pages: 0,
    total: 0,
    current: 1,
    error: null as string | null,
    department: {} as Department,
  }),
  actions: {
    async fetchDepartments(pageNum: number, pageSize: number, name?: string | null) {
      const res = await departmentAxios.fetchDepartmentPage(pageNum, pageSize, name)
      const pageData = res.data ?? {
        records: [],
        total: 0,
        pages: 0,
        current: 0,
      }
      this.departmentData = pageData.records
      this.pages = pageData.pages
      this.total = pageData.total
      this.current = pageData.current
      return pageData.records as Department[]
    },
    async fetchDepartment(id: number) {
      try {
        const res = await departmentAxios.fetchDepartment(id)
        this.department = res.data ?? {
          id: 0,
          name: '',
          isDeleted: 0,
        }
      } finally {
      }
      return this.department
    },
    async fetchUpdateDepartment(department: Department) {
      try {
        const res = await departmentAxios.updateDepartment(department)
        this.code = res.code ?? 0
      } finally {
      }
      return this.code
    },
    async fetchDeleteDepartment(ids: number[]) {
      try {
        const res = await departmentAxios.deleteDepartment(ids)
        this.code = res.code ?? 0
      } finally {
      }
      return this.code
    },
    setTableData(data: Department[]) {
      this.departmentData = data
    },
    getTableData() {
      return this.departmentData
    },
  },
})
