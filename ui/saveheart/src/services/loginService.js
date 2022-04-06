import { publicRequest, request } from '../services/httpService'
// import { API_BASE_URL } from '../constants/index'

const URL = '/api' + '/auth'

export async function getTokenApi (queryData) {
  return request({
    url: URL + '/login',
    method: 'post',
    body: queryData
  })
}
export async function registerApi (queryData) {
  return publicRequest({
    url: URL + '/register',
    method: 'post',
    body: queryData
  })
}
