import React from 'react'
import { withSiteData, Link } from 'react-static'
//
import logoImg from '../logo.png'

export default withSiteData(() => (
  <div>
    <h1 style={{ textAlign: 'center' }}>Welcome to</h1>
    <img src={logoImg} alt="" style={{ display: 'block', margin: '0 auto' }} />
    <ul>
      <li><Link to="/video/8286">video 8286</Link></li>
      <li><Link to="/video/8788">video 8788</Link></li>
    </ul>
  </div>
))
