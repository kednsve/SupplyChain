import axios from 'axios'
import type { ResponseResult } from '@/types/ResponseResult.ts'
import type { ResultPage } from '@/types/ResultPage.ts'
import type { OrderItems } from '@/types/orderItems.ts'
import { baseURL } from '@/global/variables.ts'

export const useOrderItemsAxios = () => {
  // 请求地址
  const url = `${baseURL}/api/orderItems`
  const fetchOrderItemsPage = async (
    pageNum: number,
    pageSize: number,
    productId?: number | null,
    productName?: string | null,
    quantityMin?: number | null,
    quantityMax?: number | null,
    unitPriceMin?: number | null,
    unitPriceMax?: number | null,
    totalMin?: number | null,
    totalMax?: number | null
  ) => {
    const data: any = {
      page: pageNum,
      pageSize: pageSize,
    }
    if (productId) {
      data.productId = productId
    }
    if (productName) {
      data.productName = productName
    }
    if (quantityMin) {
      data.quantityMin = quantityMin
    }
    if (quantityMax) {
      data.quantityMax = quantityMax
    }
    if (unitPriceMin) {
      data.unitPriceMin = unitPriceMin
    }
    if (unitPriceMax) {
      data.unitPriceMax = unitPriceMax
    }
    if (totalMin){
      data.totalMin = totalMin
    }
    if (totalMax){
      data.totalMax = totalMax
    }
    const res = await axios.post(`${url}`, data)
    return res.data as ResponseResult<ResultPage<OrderItems>>
  }
  const fetchOrderItems = async (orderId: number) => {
    const res = await axios.get(`${url}/${orderId}`)
    return res.data as ResponseResult<OrderItems[]>
  }
  const updateOrderItems = async (orderItems: OrderItems) => {
    const res = await axios.put(`${url}`, orderItems)
    return res.data as ResponseResult
  }
  const deleteOrderItems = async (ids: number[]) => {
    const res = await axios.delete(`${url}/${ids}`)
    return res.data as ResponseResult
  }
  return {
    fetchOrderItemsPage,
    fetchOrderItems,
    updateOrderItems,
    deleteOrderItems,
  }
}
