import React from 'react'
import styled from 'styled-components'
import Sidebar from './Sidebar'

const Container = styled.div`
  [role='main'] {
    padding-top: 3.5rem;
  }
`

const Layout = ({ children }) => (
  <Container className="container-fluid">
    <div className="row">
      <Sidebar />
      <main role="main" className="col-md-9 ml-sm-auto col-lg-10 px-2">
        {children}
      </main>
    </div>
  </Container>
)

export default Layout
