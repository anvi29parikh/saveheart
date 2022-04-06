import axios from 'axios'
import { CookieGet, CookieSet, CookieRemove } from '../lib/cookie'
import { LOGIN_PAGE_URL } from '../constants/index'
import { Notify } from 'quasar'

export const request = (options) => {
  const config = {
    headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' },
    url: options.url,
    method: options.method,
    data: options.body
  }

  if (CookieGet('token')) {
    config.headers.Authorization = 'Bearer ' + CookieGet('token')
  }

  if (navigator.onLine) {
    return axios.request(config).then(response => {
      if (response.config.headers && response.headers.authorization && !config.url.includes('dashboard') &&
      !config.url.includes('logout')) {
        CookieSet('token', response.headers && response.headers.authorization)
      }
      return response.data
    }).catch((error) => {
      handleError(error)
    })
  } else {
    return {
      message: 'INTERNET DISCONNECTED'
    }
  }
}

export const publicRequest = (options) => {
  const config = {
    headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' },
    url: options.url,
    method: options.method,
    data: options.body
  }

  if (navigator.onLine) {
    return axios.request(config).then(response => {
      if (response.config.headers && response.headers.authorization) {
        CookieSet('token', response.headers && response.headers.authorization)
      }
      return response.data
    }).catch((error) => {
      if (!window.location.href.includes('/login') && error.response && (error.response.status === 401 || error.response.status === 403)) {
        handleErrorRedirection()
      } else {
        throw error
      }
    })
  } else {
    return {
      message: 'INTERNET DISCONNECTED'
    }
  }
}

function handleError (error) {
  if (!window.location.href.includes('/login') && error.response && (error.response.status === 401 || error.response.status === 403)) {
    handleErrorRedirection()
  } else if (error.response && error.response.status === 503) {
    Notify.create({
      color: 'negative',
      icon: 'fa fa-times-circle',
      position: 'top-right',
      message: error.response.data.message,
      timeout: 3000,
      actions: [
        { label: 'Dismiss', color: 'warning' }
      ]
    })
  } else if (error.response && error.response.status === 502) {
    Notify.create({
      color: 'negative',
      icon: 'fa fa-times-circle',
      position: 'top-right',
      message: 'Service is unavailable!',
      timeout: 3000,
      actions: [
        { label: 'Dismiss', color: 'warning' }
      ]
    })
  } else {
    throw error
  }
}

function handleErrorRedirection () {
  CookieRemove()
  if (window.location.href.includes('/login')) {
    window.location.reload()
  } else if (window.location.href.includes('/logout')) {
    window.location.href = LOGIN_PAGE_URL
  } else {
    window.location.href = LOGIN_PAGE_URL + '?redirect=' + window.location.href + ''
  }
}

export default {
  get: axios.get,
  post: axios.post,
  put: axios.put,
  delete: axios.delete,
  patch: axios.patch
}
