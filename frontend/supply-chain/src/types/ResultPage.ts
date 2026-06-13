export interface ResultPage<T = any> {
  current: number
  size: number
  pages: number
  total: number
  records: T[]
}
