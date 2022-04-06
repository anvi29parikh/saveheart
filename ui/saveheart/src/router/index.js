import { route } from 'quasar/wrappers'
import { createRouter, createMemoryHistory, createWebHistory, createWebHashHistory } from 'vue-router'
import routes from './routes'
import { isEmpty } from 'lodash-es'
import { hideLoader, isAuthenticated, setPageTitle, showLoader } from 'src/helpers/routerHelpers'
import { LOGIN_PAGE_URL } from 'src/constants'

/*
 * If not building with SSR mode, you can
 * directly export the Router instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Router instance.
 */

export default route(function ({ store }) {
  const createHistory = process.env.SERVER
    ? createMemoryHistory
    : (process.env.VUE_ROUTER_MODE === 'history' ? createWebHistory : createWebHashHistory)

  const Router = createRouter({
    scrollBehavior: () => ({ left: 0, top: 0 }),
    routes,

    // Leave this as is and make changes in quasar.conf.js instead!
    // quasar.conf.js -> build -> vueRouterMode
    // quasar.conf.js -> build -> publicPath
    history: createHistory(process.env.MODE === 'ssr' ? void 0 : process.env.VUE_ROUTER_BASE)
  })

  Router.beforeEach(async (to, from, next) => {
    showLoader()
    console.log('>>>>to>>>', to)
    setPageTitle(to)
    if (to.meta.requiredAuth) {
      if (await isAuthenticated()) {
        console.log('1')
        if (store && isEmpty(store.getters['authStore/getUserProfileDetails'])) {
          console.log('2')
          console.log('>>>sotre>>', store)
          await store.dispatch('authStore/setUserDetails')
        }
        console.log(">>>>>store.getters['authStore/getUserProfileDetails']>>>", store.getters['authStore/getUserProfileDetails'])
        if (isEmpty(to.meta.roles)) {
          console.log('3')
          next()
        } else if (to.meta.roles.includes(store.getters['authStore/getUserProfileDetails'][0].role.roleName)) {
          console.log('4')
          next()
        } else {
          console.log('5')
          next('/access-denied')
        }
      } else {
        window.location.href = LOGIN_PAGE_URL
      }
    } else {
      console.log('6')
      next()
    }
  })
  Router.afterEach((to, from) => {
    hideLoader()
  })
  return Router
})
