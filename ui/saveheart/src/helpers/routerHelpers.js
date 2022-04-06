import { CookieGet } from '../lib/cookie'
import { isEmpty, intersection } from 'lodash-es'
import { Loading, QSpinnerTail } from 'quasar'
const setPageTitle = (to) => {
  document.title = (to.meta && to.meta.title) ? to.meta.title + ' | Save Heart' : 'Save Heart'
}

const isAuthenticated = async () => {
  const token = CookieGet('token')
  const isTokenAvailable = !isEmpty(token)
  if (!isTokenAvailable) {
    return false
  }
  return true
}

const showLoader = (msg) => {
  Loading.show({
    spinnerColor: 'primary',
    message: (msg) ? `Please wait... <br>${msg}` : 'Please wait...',
    spinner: QSpinnerTail
  })
}

const hideLoader = () => {
  Loading.hide()
}

const hasPageAccess = (moduleRoles, userRoles) => {
  return !isEmpty(intersection(moduleRoles, userRoles))
}

export {
  setPageTitle,
  isAuthenticated,
  showLoader,
  hideLoader,
  hasPageAccess
}
