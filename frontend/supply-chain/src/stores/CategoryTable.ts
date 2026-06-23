import { defineStore } from 'pinia'
import type { Category } from '@/types/category.ts'
import { useCategoryAxios } from '@/utils/categoryAxios.ts'

const categoryAxios = useCategoryAxios()

export const useCategoryStore = defineStore('CategoryTable', {
  state: () => ({
    code: 0,
    categoryData: [] as Category[],
    pages: 0,
    total: 0,
    current: 1,
    error: null as string | null,
    category: {} as Category,
  }),
  actions: {
    async fetchCategories(pageNum: number, pageSize: number, name?: string | null) {
      const res = await categoryAxios.fetchCategoryPage(pageNum, pageSize, name)
      const pageData = res.data ?? {
        records: [],
        total: 0,
        pages: 0,
        current: 0,
      }
      this.categoryData = pageData.records
      this.pages = pageData.pages
      this.total = pageData.total
      this.current = pageData.current
      return pageData.records as Category[]
    },
    async fetchCategory(id: number) {
      try {
        const res = await categoryAxios.fetchCategory(id)
        this.category = res.data ?? {
          id: 0,
          name: '',
          isDeleted: 0,
        }
      } finally {
      }
      return this.category
    },
    async fetchUpdateCategory(category: Category) {
      try {
        const res = await categoryAxios.updateCategory(category)
        this.code = res.code ?? 0
      } finally {
      }
      return this.code
    },
    async fetchDeleteCategory(ids: number[]) {
      try {
        const res = await categoryAxios.deleteCategory(ids)
        this.code = res.code ?? 0
      } finally {
      }
      return this.code
    },
    setTableData(data: Category[]) {
      this.categoryData = data
    },
    getTableData() {
      return this.categoryData
    },
  },
})
