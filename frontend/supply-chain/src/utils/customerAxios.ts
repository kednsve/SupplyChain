import axios from 'axios'
import type { ResponseResult } from '@/types/ResponseResult.ts'
import type { Customer } from '@/types/customer.ts'
import type { ResultPage } from '@/types/ResultPage.ts'
import { baseURL } from '@/global/variables.ts'

export const useCustomerAxios = () => {
  // 请求地址
  const url = `${baseURL}/api/customer`
  const fetchCustomerPage = async (
    pageNum: number,
    pageSize: number,
    name?: string | null,
    segment?: string | null,
  ) => {
    const data: any = {
      page: pageNum,
      pageSize: pageSize,
    }
    if (name) {
      data.name = name
    }
    if (segment) {
      data.segment = segment
    }
    const res = await axios.post(`${url}`, data)
    return res.data as ResponseResult<ResultPage<Customer>>
  }
  const fetchCustomer = async (id: number) => {
    const res = await axios.get(`${url}/${id}`)
    return res.data as ResponseResult<Customer>
  }
  const updateCustomer = async (customer: Customer) => {
    const res = await axios.put(`${url}`, customer)
    return res.data as ResponseResult
  }
  const deleteCustomer = async (ids: number[]) => {
    const res = await axios.delete(`${url}/${ids}`)
    return res.data as ResponseResult
  }
  return {
    fetchCustomerPage,
    fetchCustomer,
    updateCustomer,
    deleteCustomer,
  }
}
