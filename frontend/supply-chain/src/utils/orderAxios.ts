import axios from 'axios'
import type { ResponseResult } from '@/types/ResponseResult.ts'
import type { ResultPage } from '@/types/ResultPage.ts'
import type { Order } from '@/types/order.ts'
import { baseURL } from '@/global/variables.ts'

export const useOrderAxios = () => {
  // 请求地址
  const url = `${baseURL}/api/order`
  const fetchOrderPage = async (
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
  ) => {
    const data: any = {
      page: pageNum,
      pageSize: pageSize,
    }
    if (start) {
      data.start = start
    }
    if (end) {
      data.end = end
    }
    if (customerId) {
      data.customerId = customerId
    }
    if (salesMin) {
      data.salesMin = salesMin
    }
    if (salesMax) {
      data.salesMax = salesMax
    }
    if (status) {
      data.status = status
    }
    if (region) {
      data.region = region
    }
    if (country) {
      data.country = country
    }
    if (city) {
      data.city = city
    }
    const res = await axios.post(`${url}`, data)
    return res.data as ResponseResult<ResultPage<Order>>
  }
  const fetchOrder = async (id: number) => {
    const res = await axios.get(`${url}/${id}`)
    return res.data as ResponseResult<Order>
  }
  const updateOrder = async (order: Order) => {
    const res = await axios.put(`${url}`, order)
    return res.data as ResponseResult
  }
  const deleteOrder = async (ids: number[]) => {
    const res = await axios.delete(`${url}/${ids}`)
    return res.data as ResponseResult
  }
  return {
    fetchOrderPage,
    fetchOrder,
    updateOrder,
    deleteOrder,
  }
}
