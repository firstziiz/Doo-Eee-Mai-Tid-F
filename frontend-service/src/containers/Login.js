import React from 'react'
import { withRouter } from 'react-static'

import LeftPanel from '../components/Login/LeftSide'
import LoginPanel from '../components/Login/LoginPanel'

class Login extends React.Component {

  render() {
    return (
      <div>
        <div className="container-fluid text-center">
          <div className="row">
            <LeftPanel />
            <LoginPanel {...this.props} />
          </div>
        </div>
      </div>
    )
  }
}

export default withRouter(Login)
