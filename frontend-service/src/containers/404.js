import React from 'react'
import { Row } from 'antd'

export default () => (
  <Row type="flex" justify="center" align="middle" style={{ height: '100vh', flexDirection: 'column' }} >
    <h1 style={{ fontSize: 120, margin: 0 }}>404</h1>
    <h3 style={{ margin: 20 }}>Oops! the page you looking for is not found.</h3>
    <a style={{ color: 'blue', fontSize: 18 }} href='/'>Let's go back home</a>
  </Row>
)
