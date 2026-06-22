import axios from 'axios'
import type { ResponseResult } from '@/types/ResponseResult.ts'
import type { ResultPage } from '@/types/ResultPage.ts'
import type { Category } from '@/types/category.ts'
import { baseURL } from '@/global/variables.ts'

export const useCategoryAxios = () => {
  // 请求地址
  const url = `${baseURL}/api/category`
  const fetchCategoryPage = async (
    pageNum: number,
    pageSize: number,
    name?: string | null,
  ) => {
    const data: any = {
      page: pageNum,
      pageSize: pageSize,
    }
    if (name) {
      data.name = name
    }

    const res = await axios.post(`${url}`, data)
    return res.data as ResponseResult<ResultPage<Category>>
  }
  const fetchCategory = async (id: number) => {
    const res = await axios.get(`${url}/${id}`)
    return res.data as ResponseResult<Category>
  }
  const updateCategory = async (category: Category) => {
    const res = await axios.put(`${url}`, category)
    return res.data as ResponseResult
  }
  const deleteCategory = async (ids: number[]) => {
    const res = await axios.delete(`${url}/${ids}`)
    return res.data as ResponseResult
  }
  return {
    fetchCategoryPage,
    fetchCategory,
    updateCategory,
    deleteCategory,
  }
}
