import React from 'react'
import styled from 'styled-components'
import Sidebar from './Sidebar'
import Navbar from './Navbar'

const Container = styled.div`
  [role='main'] {
    padding-top: 3.5rem;
  }
`

const Layout = ({ children }) => (
  <React.Fragment>
    <Navbar />
    <Container className="container-fluid">
      <div className="row">
        <Sidebar />
        <main role="main" className="col-md-9 ml-sm-auto col-lg-10 px-2">
          {children}
        </main>
      </div>
    </Container>
  </React.Fragment>
)

export default Layout
