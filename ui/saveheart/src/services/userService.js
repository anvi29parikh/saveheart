import { request } from '../services/httpService'
// import { API_BASE_URL } from '../constants/index'

const URL = '/api' + '/user'

export async function getUserDetails () {
  return request({
    url: URL + '/detail',
    method: 'get'
  })
}

export async function getPredictionData (queryData) {
  return request({
    url: URL + '/predict',
    method: 'post',
    body: queryData
  })
}
