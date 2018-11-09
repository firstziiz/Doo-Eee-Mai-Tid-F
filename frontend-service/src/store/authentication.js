import { observable } from 'mobx'

class Authentication {
  @observable
  token = 'sit-cloudnative-token'
}

let store

function initStore() {
  if (store) return store
  store = new Authentication()
  return store
}

export default initStore()
