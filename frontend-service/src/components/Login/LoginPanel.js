import React from 'react'
import styled from 'styled-components'
import { Alert } from 'antd'
import { observer, inject } from 'mobx-react'
import { Redirect } from 'react-static'

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

@inject('userStore')
@observer
class LoginPanel extends React.Component {

  onSubmit = async e => {
    e.preventDefault()
    const responseStatus = await this.props.userStore.login()
    if (responseStatus) {
      this.props.history.push('/')
    }
  }

  render() {
    const {
      userId,
      password,
      authError,
      setField,
      loading
    } = this.props.userStore
    return (
      <LoginContainer>
        <form onSubmit={this.onSubmit}>
          <AppTitle />
          <h4 className="mb-3">Next Generation of E-learning</h4>
          {authError && (
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
              id="userId-input"
              className="form-control"
              onChange={e => setField('userId', e.target.value)}
              value={userId}
            />
          </div>
          <div className="form-group text-left">
            <label htmlFor="">Password</label>
            <Input
              type="password"
              id="password-input"
              className="form-control"
              onChange={e => setField('password', e.target.value)}
              value={password}
            />
          </div>
          <Button
            className="mt-3"
            title="SIGN IN"
            type="submit"
            loading={loading}
            block
          />
        </form>
      </LoginContainer>
    )
  }
}

export default LoginPanel
