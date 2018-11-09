import React from 'react'
import Cookies from 'js-cookie'

import { tokenName } from '../utils/env'

class Logout extends React.Component {
  componentWillMount() {
    Cookies.remove(tokenName)
    window.location = '/login'
  }

  render() {
    return <div />
  }
}

export default Logout
