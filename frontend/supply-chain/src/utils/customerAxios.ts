import axios from 'axios'
import type { ResponseResult } from '@/types/ResponseResult.ts'
import type { Customer } from '@/types/customer.ts'
import type { ResultPage } from '@/types/ResultPage.ts'

export const useCustomerAxios = () => {
  // 请求地址
  const baseURL = 'http://localhost:8080/api/customer'
  const fetchCustomerPage = async (pageNum: number, pageSize: number, name?: string | null,segment?:string|null) => {
    const data: any = {
      page: pageNum,
      pageSize: pageSize,
    }
    if (name) {
      data.name = name
    }
    if (segment){
      data.segment=segment
    }
    const res = await axios.post(`${baseURL}`, data)
    return res.data as ResponseResult<ResultPage<Customer>>
  }
  const fetchCustomer = async (id: number) => {
    const res = await axios.get(`${baseURL}/${id}`)
    return res.data as ResponseResult<Customer>
  }
  const updateCustomer = async (customer: Customer) => {
    const res = await axios.put(`${baseURL}`, customer)
    return res.data as ResponseResult
  }
  const deleteCustomer = async (ids: number[]) => {
    const res = await axios.delete(`${baseURL}/${ids}`)
    return res.data as ResponseResult
  }
  return {
    fetchCustomerPage,
    fetchCustomer,
    updateCustomer,
    deleteCustomer,
  }
}
