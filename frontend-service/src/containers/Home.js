import React from 'react'
import Layout from '../components/Core/Layout'
import requireAuth from '../utils/requireAuth'

@requireAuth
class Home extends React.Component {
  render() {
    return (
      <Layout>
        <h1>
          Welcome to SITFlix, <small className="d-block">The Next Generation of E-learning</small>
        </h1>
      </Layout>
    )
  }
}

export default Home
