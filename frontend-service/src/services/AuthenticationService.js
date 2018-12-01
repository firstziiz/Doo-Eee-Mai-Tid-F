import api from '../utils/api'

let AUTH_URL = `https://authentication-service-dot-sit-cloudnative.appspot.com`

if (typeof window !== 'undefined') {
  AUTH_URL = window.env.authenticationServiceURL
}

const AuthenticationService = {
  login: (userId, password) => {
    const data = new FormData()
    data.append('userId', userId)
    data.append('password', password)

    return api.post(`${AUTH_URL}/login`, data)
  },
  me: () => {
    return api.get(`${AUTH_URL}/me`)
  },
  refreshToken: () => {
    return api.get(`${AUTH_URL}/refresh-token`)
  }
}

export default AuthenticationService
