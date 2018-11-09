import React from 'react'
import styled from 'styled-components'
import Cookies from 'js-cookie'
import { withRouter } from 'react-static'
import Sidebar from './Sidebar'
import Navbar from './Navbar'
import { tokenName, authenticationServiceURL } from '../../utils/env'
import axios from '../../utils/axios-creator'

const Container = styled.div`
  [role='main'] {
    padding-top: 3.5rem;
  }
`

class Layout extends React.Component {
  async componentWillMount() {
    const token = Cookies.get(tokenName)
    if (!token) {
      this.props.history.push('/login')
    }

    const me = await axios({
      method: 'get',
      url: `${authenticationServiceURL}/me`
    })

    // console.log(me)
  }

  render() {
    return (
      <React.Fragment>
        <Navbar />
        <Container className="container-fluid">
          <div className="row">
            <Sidebar />
            <main role="main" className="col-md-9 ml-sm-auto col-lg-10 px-3">
              {this.props.children}
            </main>
          </div>
        </Container>
      </React.Fragment>
    )
  }
}

export default withRouter(Layout)
