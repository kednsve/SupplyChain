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
    quantity?: number | null,
    unitPriceLow?: number | null,
    unitPriceHigh?: number | null,
  ) => {
    const data: any = {
      page: pageNum,
      pageSize: pageSize,
    }
    if (productId) {
      data.productId = productId
    }
    if (quantity) {
      data.quantity = quantity
    }
    if (unitPriceLow) {
      data.unitPriceLow = unitPriceLow
    }
    if (unitPriceHigh) {
      data.unitPriceHigh = unitPriceHigh
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
