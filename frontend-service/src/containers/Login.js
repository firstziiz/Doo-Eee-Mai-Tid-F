import React from 'react'
import store from '../utils/store'
import { withRouter } from 'react-static'

import { authenticationServiceURL } from '../utils/env'
import LeftPanel from '../components/Login/LeftSide'
import LoginPanel from '../components/Login/LoginPanel'
// import axios from '../utils/axios-creator'
import { observer, inject } from 'mobx-react'

@inject('userStore')
@observer
class Login extends React.Component {
  state = {
    authError: false,
    loading: false
  }

  signIn = async ({ userId, password }) => {
    try {
      // console.log(userId, password)
      this.setState({
        loading: true
      })

      await this.props.userStore.login(userId, password)
      this.props.history.push('/')
    } catch (error) {
      this.setState({
        authError: true,
        loading: false
      })
    }
  }

  render() {
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
