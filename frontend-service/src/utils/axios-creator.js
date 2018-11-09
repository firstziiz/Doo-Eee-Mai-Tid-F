import axios from 'axios'
import Cookies from 'js-cookie'

import { tokenName } from './env'

const token = Cookies.get(tokenName)

const axiosInstance = axios.create({
  headers: { Authorization: `JWT ${token}` }
})

export default axiosInstance
