import axios from 'axios'
import type { ResponseResult } from '@/types/ResponseResult.ts'
import type { ResultPage } from '@/types/ResultPage.ts'
import type { Product } from '@/types/product.ts'
import { baseURL } from '@/global/variables.ts'

export const useProductAxios = () => {
  // 请求地址
  const url = `${baseURL}/api/product`
  const fetchProductPage = async (
    pageNum: number,
    pageSize: number,
    name?: string | null,
    priceLow?: number | null,
    priceHigh?: number | null,
    categoryId?: number | null,
    categoryName?: string | null,
    departmentId?: number | null,
    departmentName?: string | null,
  ) => {
    const data: any = {
      page: pageNum,
      pageSize: pageSize,
    }
    if (name) {
      data.name = name
    }
    if (priceLow) {
      data.priceLow = priceLow
    }
    if (priceHigh) {
      data.priceHigh = priceHigh
    }
    if (categoryId) {
      data.categoryId = categoryId
    }
    if (categoryName) {
      data.categoryName = categoryName
    }
    if (departmentId) {
      data.departmentId = departmentId
    }
    if (departmentName) {
      data.departmentName = departmentName
    }
    console.log('data', data)
    const res = await axios.post(`${url}`, data)
    console.log('res', res.data)
    return res.data as ResponseResult<ResultPage<Product>>
  }
  const fetchProduct = async (id: number) => {
    const res = await axios.get(`${url}/${id}`)
    return res.data as ResponseResult<Product>
  }
  const updateProduct = async (product: Product) => {
    const res = await axios.put(`${url}`, product)
    return res.data as ResponseResult
  }
  const deleteProduct = async (ids: number[]) => {
    const res = await axios.delete(`${url}/${ids}`)
    return res.data as ResponseResult
  }
  return {
    fetchProductPage,
    fetchProduct,
    updateProduct,
    deleteProduct,
  }
}
