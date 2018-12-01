import React from 'react'
import store from '../utils/store'

class Logout extends React.Component {
  componentDidMount() {
    store.removeAccessToken()
    window.location = '/login'
  }

  render() {
    return <div />
  }
}

export default Logout
