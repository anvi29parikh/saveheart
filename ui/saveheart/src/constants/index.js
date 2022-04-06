const HOST = location.hostname

let ENV_API_BASE_URL
let ENV_APP_BASE_URL
let ENV_BASE_COOKIE

if (HOST.includes('localhost')) {
  ENV_APP_BASE_URL = 'http://localhost:8080'
  ENV_API_BASE_URL = 'http://localhost:8081'

  ENV_BASE_COOKIE = 'localhost'
} else if (HOST.includes('testing.saveheart.com')) {
  // testing property
} else {
  // production
}

export const APP_BASE_URL = ENV_APP_BASE_URL
export const AUTH_BASE_URL = ENV_APP_BASE_URL

export const API_BASE_URL = ENV_API_BASE_URL
export const BASE_COOKIE = ENV_BASE_COOKIE
export const LOGIN_PAGE_URL = APP_BASE_URL + '/#/login'
export const DASHBOARD_PAGE_URL = AUTH_BASE_URL + '/#/dashboard'
export const ACCESS_DENIED_URL = APP_BASE_URL + '/#/dashboard?accessDenied='

export const DATE_FORMAT = 'MM-DD-YYYY'
export const DATE_TIME_FORMAT = 'MM-DD-YYYY HH:mm:ss'
export const DATE_TIME_AM_PM_FORMAT = 'MM-DD-YYYY hh:mm A'
