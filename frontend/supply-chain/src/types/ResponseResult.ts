export interface ResponseResult<T = any> {
  code: number
  msg: string
  data: T | null
}
