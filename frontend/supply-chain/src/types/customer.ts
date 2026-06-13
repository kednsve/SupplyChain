export interface Customer {
  id: number
  name: string
  segment: string
  isDeleted: number
}
export interface CustomerPage {
  records: Customer[]
}
