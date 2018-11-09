import axios from 'axios'
import Cookies from 'js-cookie'

import { tokenName } from './env'

const token = Cookies.get(tokenName)

const axiosInstance = axios.create({
  headers: { Authorization: `${token}` }
})

export default axiosInstance
