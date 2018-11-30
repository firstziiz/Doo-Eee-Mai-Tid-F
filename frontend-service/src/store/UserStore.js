import { observable, action } from 'mobx'
import { message } from 'antd'

import store from '../utils/store'
import AuthenticationService from '../services/AuthenticationService'

export default class UserStore {
  constructor(rootStore) {
    this.rootStore = rootStore
  }

  @observable
  userId = ''

  @observable
  password = ''

  @observable
  authError = false

  @observable
  loading = false

  @observable
  user = {}

  // for checking when you are loging in.
  @observable
  logingIn = false

  // for checking when you have logged in.
  @observable
  authenticated = false

  @action
  setField = (field, value) => {
    this[field] = value
  }

  @action
  login = async () => {
    this.setLoginIn(true)
    await store.removeAccessToken()
    message.loading('กำลังเข้าสู่ระบบ')
    try {
      this.authError = false
      this.loading = true
      const { userId, password } = this
      const result = await AuthenticationService.login(userId, password)
        .then(async resp => {
          await store.setAccessToken(resp.data.token)
          await this.getProfile()
          message.success('เข้าสู่ระบบสำเร็จ กรุณารอซักครู่')
          return true
        })
      return result
    } catch (error) {
      this.authError = true
      message.error('เข้าสู่ระบบไม่สำเร็จ กรุณาติดต่อผู้ดูแลระบบ')
      console.log(error)
      return false
    } finally {
      this.setLoginIn(false)
      this.loading = false
      this.setField('userId', '')
      this.setField('password', '')
    }
  }

  @action
  getProfile = async () => {
    try {
      await AuthenticationService.me().then(resp => {
        this.setUser(resp.data)
        this.setLoggedIn()
      })
    } catch (error) {
      console.log(error)
    }
  }

  @action
  logout = async () => {
    this.authenticated = false
    await store.removeAccessToken()
  }

  @action
  setUser = data => {
    this.user = data || null
  }

  @action
  setLoginIn = bool => {
    this.logingIn = bool
  }

  @action
  setLoggedIn = () => {
    this.authenticated = true
  }

  @action
  getUserName = () => `${this.user.first_name} ${this.user.last_name} (${this.user.role})`

  // Login Modal
  @observable
  loginModalVisible = false

  @action
  setLoginModal = bool => {
    this.loginModalVisible = bool
  }
}
