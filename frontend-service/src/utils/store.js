let store = {
  getAccessToken: () => null,
  setAccessToken: () => null,
  removeAccessToken: () => null
}

if (typeof window !== 'undefined') {
  store = {
    getAccessToken: () => window.localStorage.getItem('dooe-token'),
    setAccessToken: token => window.localStorage.setItem('dooe-token', token),
    removeAccessToken: () => window.localStorage.removeItem('dooe-token')
  }
}

export default store
