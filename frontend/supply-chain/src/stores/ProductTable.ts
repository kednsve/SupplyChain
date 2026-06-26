import { defineStore } from 'pinia'
import type { Product } from '@/types/product.ts'
import { useProductAxios } from '@/utils/productAxios.ts'

const productAxios = useProductAxios()

export const useProductStore = defineStore('ProductTable', {
  state: () => ({
    code: 0,
    productData: [] as Product[],
    pages: 0,
    total: 0,
    current: 1,
    error: null as string | null,
    product: {} as Product,
  }),
  actions: {
    async fetchProducts(
      pageNum: number,
      pageSize: number,
      name?: string | null,
      priceLow?: number | null,
      priceHigh?: number | null,
      categoryId?: number | null,
      categoryName?: string | null,
      departmentId?: number | null,
      departmentName?: string | null,
    ) {
      const res = await productAxios.fetchProductPage(
        pageNum,
        pageSize,
        name,
        priceLow,
        priceHigh,
        categoryId,
        categoryName,
        departmentId,
        departmentName,
      )
      const pageData = res.data ?? {
        records: [],
        total: 0,
        pages: 0,
        current: 0,
      }
      this.productData = pageData.records
      this.pages = pageData.pages
      this.total = pageData.total
      this.current = pageData.current
      return pageData.records as Product[]
    },
    async fetchProduct(id: number) {
      try {
        const res = await productAxios.fetchProduct(id)
        this.product = res.data ?? {
          id: 0,
          name: '',
          image: '',
          price: 0,
          categoryId: 0,
          categoryName: '',
          departmentId: 0,
          departmentName: '',
        }
      } finally {
      }
      return this.product
    },
    async fetchUpdateProduct(product: Product) {
      try {
        const res = await productAxios.updateProduct(product)
        this.code = res.code ?? 0
      } finally {
      }
      return this.code
    },
    async fetchDeleteProduct(ids: number[]) {
      try {
        const res = await productAxios.deleteProduct(ids)
        this.code = res.code ?? 0
      } finally {
      }
      return this.code
    },
    setTableData(data: Product[]) {
      this.productData = data
    },
    getTableData() {
      return this.productData
    },
  },
})
