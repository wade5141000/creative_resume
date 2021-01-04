import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('../views/Home.vue')
  },
  {
    path: '/login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/user',
    component: () => import('../views/User.vue'),
    meta:{
      requireAuth: true
    }
  },
  {
    path: '/sample',
    component: () => import('../views/Sample.vue'),
  },
  {
    path: '/table',
    component: () => import('../views/Table.vue'),
  },
  {
    path: '*',
    redirect: '/'
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

router.beforeEach((to, from, next)=> {
  if(to.meta.requireAuth){
    if (store.state.isLogin) {
      next();
    }
    else {
      next({
        path: '/login',
        query: {redirect: to.fullPath} // 將跳轉的路由path作為引數，登入成功後跳轉到該路由
      })
    }
  }else{
    next();
  }
})

export default router