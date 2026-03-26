import { createRouter, createWebHistory } from 'vue-router'
import { useAdminStore } from '@/stores/admin'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    {
      path: '/login',
      name: 'Login',
      component: () => import('../views/Login.vue'),
    },
    {
      path: '/index',
      name: 'Index',
      redirect: { name: 'Welcome' },
      component: () => import('../views/index.vue'),

      children: [
        {
          path: '/welcome',
          name: 'Welcome',
          component: () => import('../views/WelcomeVue.vue'),
        },
        {
          path: '/admin',
          name: 'Admin',
          component: () => import('../views/admin/List.vue'),
        },
      ],
    },
  ],
})

export default router


router.beforeEach(async (to, from) => {
  if (to.path == '/login') {
    return true;
    // 将用户重定向到登录页面
  }
  else
  {
     //判断有没有token
     if(!useAdminStore().getToken())
     {
        return {path:'/login'};
     }
  }
})
