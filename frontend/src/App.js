import React from 'react'
import { Router, Link, Route, Switch } from 'react-static'
import styled, { injectGlobal } from 'styled-components'
import { hot } from 'react-hot-loader'
import Video from './containers/Video'
//
import Routes from 'react-static-routes'

injectGlobal`
  body {
    font-family: 'HelveticaNeue-Light', 'Helvetica Neue Light', 'Helvetica Neue', Helvetica, Arial,
      'Lucida Grande', sans-serif;
    font-weight: 300;
    font-size: 16px;
    margin: 0;
    padding: 0;
  }
`

const AppStyles = styled.div`
  a {
    text-decoration: none;
    color: #108db8;
    font-weight: bold;
  }

  nav {
    width: 100%;
    background: #108db8;

    a {
      color: white;
      padding: 1rem;
      display: inline-block;
    }
  }

  .content {
    padding: 1rem;
  }

  img {
    max-width: 100%;
  }
`

const App = () => (
  <Router>
    <AppStyles>
      <nav>
        <Link exact to="/">Home</Link>
        <Link to="/about">About</Link>
        <Link to="/blog">Blog</Link>
      </nav>
      <Switch>
        <Route path="/video/:videoId" component={Video} />
        <Routes />
      </Switch>
    </AppStyles>
  </Router>
)

export default hot(module)(App)
