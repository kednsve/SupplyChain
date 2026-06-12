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
      children: [
        {
          path: 'home',
          component: Home,
        },
        {
          path: 'order',
          component: Order,
        },
        {
          path: 'customer',
          component: Customer,
        },
        {
          path: 'orderItems',
          component: OrderItems,
        },
        {
          path: 'product',
          component: Product,
        },
        {
          path: 'department',
          component: Department,
        },
        {
          path: 'category',
          component: Category,
        },
      ],
    },
  ],
})

export default router
