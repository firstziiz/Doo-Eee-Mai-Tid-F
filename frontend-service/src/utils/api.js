import axios from 'axios'
import store from './store'

const createApiInstance = () => {
  const http = axios.create({
    timeout: 15000,
    headers: {
      Authorization: store.getAccessToken() ? `Bearer ${store.getAccessToken()}` : ''
    }
  })

  http.interceptors.request.use(
    function(config) {
      const token = store.getAccessToken()
      if (token) config.headers.Authorization = `Bearer ${token}`
      return config
    },
    function(error) {
      return Promise.reject(error)
    }
  )

  return http
}

const handleResponse = response => {
  return Promise.resolve(response)
}

const catchError = e => Promise.reject(e.response)

export default {
  get: path =>
    createApiInstance()
      .get(path)
      .then(handleResponse)
      .catch(catchError),
  post: (path, body = {}, headers = {}) =>
    createApiInstance()
      .request({
        url: path,
        method: 'POST',
        headers,
        data: body
      })
      .then(handleResponse)
      .catch(catchError),
  patch: (path, body = {}) =>
    createApiInstance()
      .request({
        url: path,
        method: 'PATCH',
        data: body
      })
      .then(handleResponse)
      .catch(catchError),
  delete: path =>
    createApiInstance()
      .delete(path)
      .then(handleResponse)
      .catch(catchError)
}
