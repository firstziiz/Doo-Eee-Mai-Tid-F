import React from 'react'
import styled from 'styled-components'
import { Alert } from 'antd'

import AppTitle from '../Typography/AppTitle'
import Button from '../Form/Button'
import Input from '../Form/Input'

const LoginContainer = styled.div`
  width: 520px;
  padding: 80px;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;

  & form {
    width: 100%;
  }

  @media (max-width: 519px) {
    padding: 20px;
  }
`

class LoginPanel extends React.Component {
  state = {
    userId: '',
    password: ''
  }

  onSubmit = e => {
    e.preventDefault()

    this.props.onSubmit({
      userId: this.state.userId,
      password: this.state.password
    })
  }

  render() {
    return (
      <LoginContainer>
        <form onSubmit={this.onSubmit}>
          <AppTitle />
          <h4 className="mb-3">Next Generation of E-learning</h4>
          {this.props.authError && (
            <Alert
              message="Username or password is not correct."
              type="error"
              showIcon
              className="mb-3"
            />
          )}
          <div className="form-group text-left">
            <label htmlFor="">Username</label>
            <Input
              type="text"
              className="form-control"
              onChange={e => this.setState({ userId: e.target.value })}
            />
          </div>
          <div className="form-group text-left">
            <label htmlFor="">Password</label>
            <Input
              type="password"
              className="form-control"
              onChange={e => this.setState({ password: e.target.value })}
            />
          </div>
          <Button
            className="mt-3"
            title="SIGN IN"
            type="submit"
            loading={this.props.loading}
            block
          />
        </form>
      </LoginContainer>
    )
  }
}

export default LoginPanel
