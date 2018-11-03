import React from 'react'
import styled from 'styled-components'
import Sidebar from './Sidebar'

const Container = styled.div`
  [role='main'] {
    padding-top: 48px; /* Space for fixed navbar */
  }
`

const Layout = ({ children }) => (
  <Container className="container-fluid">
    <div className="row">
      <Sidebar />
      <main role="main">{children}</main>
    </div>
  </Container>
)

export default Layout
