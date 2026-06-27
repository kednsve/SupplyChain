import axios from 'axios'
import type { ResponseResult } from '@/types/ResponseResult.ts'
import type { ResultPage } from '@/types/ResultPage.ts'
import type { Department } from '@/types/department.ts'
import { baseURL } from '@/global/variables.ts'

export const useDepartmentAxios = () => {
  // 请求地址
  const url = `${baseURL}/api/department`
  const fetchDepartmentPage = async (pageNum: number, pageSize: number, name?: string | null) => {
    const data: any = {
      page: pageNum,
      pageSize: pageSize,
    }
    if (name) {
      data.name = name
    }
    const res = await axios.post(`${url}`, data)
    return res.data as ResponseResult<ResultPage<Department>>
  }
  const fetchDepartment = async (id: number) => {
    const res = await axios.get(`${url}/${id}`)
    return res.data as ResponseResult<Department>
  }
  const updateDepartment = async (department: Department) => {
    const res = await axios.put(`${url}`, department)
    return res.data as ResponseResult
  }
  const deleteDepartment = async (ids: number[]) => {
    const res = await axios.delete(`${url}/${ids}`)
    return res.data as ResponseResult
  }
  return {
    fetchDepartmentPage,
    fetchDepartment,
    updateDepartment,
    deleteDepartment,
  }
}
