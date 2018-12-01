import React from 'react'
import { inject, observer } from 'mobx-react'

const requireAuth = Component => {
  @inject('userStore')
  @observer
  class RequireAuth extends React.Component {
    componentWillMount() {
      if (!this.props.userStore.authenticated) {
        this.props.history.replace('/')
        this.props.userStore.setLoginModal(true)
      }
    }

    render() {
      return <Component {...this.props} />
    }
  }

  return RequireAuth
}

export default requireAuth
