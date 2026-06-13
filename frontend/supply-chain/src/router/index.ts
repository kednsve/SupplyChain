import { createRouter, createWebHistory } from 'vue-router'
import Frame from '@/global/frame.vue'
import Home from '@/components/home.vue'
import Order from '@/components/order.vue'
import Customer from '@/components/customer.vue'
import OrderItems from '@/components/orderItems.vue'
import Product from '@/components/product.vue'
import Department from '@/components/department.vue'
import Category from '@/components/category.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: Frame,
      redirect: '/home',
      meta: { title: '总览' },
      children: [
        {
          path: 'home',
          component: Home,
          meta: { title: '总览' },
        },
        {
          path: 'order',
          component: Order,
          meta: { title: '订单信息' },
        },
        {
          path: 'customer',
          component: Customer,
          meta: { title: '用户信息' },
        },
        {
          path: 'orderItems',
          component: OrderItems,
          meta: { title: '订单商品信息' },
        },
        {
          path: 'product',
          component: Product,
          meta: { title: '产品信息' },
        },
        {
          path: 'department',
          component: Department,
          meta: { title: '总览' },
        },
        {
          path: 'category',
          component: Category,
          meta: { title: '分类信息' },
        },
      ],
    },
  ],
})

export default router
