import React from 'react'
import Cookies from 'js-cookie'
import { withRouter } from 'react-static'

import { tokenName, authenticationServiceURL } from '../utils/env'
import LeftPanel from '../components/Login/LeftSide'
import LoginPanel from '../components/Login/LoginPanel'
import axios from '../utils/axios-creator'
import { observer, inject } from 'mobx-react'

@inject('authenticationStore')
@observer
class Login extends React.Component {
  state = {
    authError: false,
    loading: false
  }

  componentWillMount() {
    const token = Cookies.get(tokenName)
    if (token) {
      this.props.history.push('/')
    }
  }

  signIn = async ({ studentId, password }) => {
    try {
      this.setState({
        loading: true
      })

      const data = new FormData()
      data.append('studentId', studentId)
      data.append('password', password)

      const response = await axios({
        method: 'post',
        url: `${authenticationServiceURL}/login`,
        data
      })

      const token = response.data.token
      Cookies.set(tokenName, token)
      window.location = '/'
    } catch (error) {
      this.setState({
        authError: true,
        loading: false
      })
    }
  }

  render() {
    const { authenticationStore } = this.props
    return (
      <div>
        <div className="container-fluid text-center">
          <div className="row">
            <LeftPanel />
            <LoginPanel
              onSubmit={this.signIn}
              loading={this.state.loading}
              authError={this.state.authError}
            />
          </div>
        </div>
      </div>
    )
  }
}

export default withRouter(Login)
