const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    redirect: '/predictor',
    children: [
      {
        path: '/predictor',
        component: () => import('pages/PredictorPage.vue'),
        meta: {
          requiredAuth: true,
          title: 'Predictor',
          roles: ['USER', 'ADMIN']
        }
      },
      {
        path: '/heart-disease',
        component: () => import('pages/HeartDisease.vue'),
        meta: {
          requiredAuth: true,
          title: 'Heart Disease',
          roles: ['USER', 'ADMIN']
        }
      },
      {
        path: '/no-heart-disease',
        component: () => import('pages/NoHeartDisease.vue'),
        meta: {
          requiredAuth: true,
          title: 'Heart Disease',
          roles: ['USER', 'ADMIN']
        }
      }
    ]
  },
  {
    path: '/predictor',
    component: () => import('pages/PredictorPage.vue'),
    meta: {
      requiredAuth: true,
      title: 'Predictor',
      roles: ['USER', 'ADMIN']
    }
  },
  {
    path: '/login',
    component: () => import('pages/LoginPage.vue'),
    meta: {
      title: 'Login'
    }
  },
  {
    path: '/register',
    component: () => import('pages/RegisterPage.vue'),
    meta: {
      title: 'Register'
    }
  },
  {
    path: '/access-denied',
    component: () => import('pages/AccessDenied.vue'),
    meta: {
      title: 'Access Denied'
    }
  },
  {
    path: '/404',
    component: () => import('pages/NotFound.vue'),
    meta: {
      title: 'Not Found'
    }
  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }
]

export default routes
