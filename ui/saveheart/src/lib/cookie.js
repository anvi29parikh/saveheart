import Cookies from 'js-cookie'
import { BASE_COOKIE } from '../constants/index'

const isDevEnvironment = process.env.NODE_ENV === 'development'

const CookieSet = (key, value) => {
  if (isDevEnvironment) {
    Cookies.set(key, value)
  } else {
    Cookies.set(key, value, { httpOnly: false, domain: BASE_COOKIE })
  }
}

const CookieGet = (key) => {
  if (key !== null && key !== undefined) {
    return Cookies.get(key)
  } else {
    return Cookies.get()
  }
}

const CookieRemove = (cookie) => {
  if (cookie !== null && cookie !== undefined) {
    if (isDevEnvironment) {
      Cookies.remove(cookie)
    } else {
      Cookies.remove(cookie, { httpOnly: false, domain: BASE_COOKIE })
    }
  } else {
    const cookies = Cookies.get()
    Object.keys(cookies).forEach((_cookie) => {
      if (isDevEnvironment) {
        Cookies.remove(_cookie)
      } else {
        Cookies.remove(_cookie, { httpOnly: false, domain: BASE_COOKIE })
      }
    })
  }
}

export {
  CookieSet,
  CookieGet,
  CookieRemove
}
