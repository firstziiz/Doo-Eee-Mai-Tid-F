import React from 'react'
import { Router, Route, Switch, Redirect } from 'react-static'
import { createGlobalStyle } from 'styled-components'
import { hot } from 'react-hot-loader'
//

import Home from './containers/Home'
import Video from './containers/Video'
import NotFound from './containers/404'
import Navbar from './components/Core/Navbar'

createGlobalStyle`
  body {
    font-family: 'HelveticaNeue-Light', 'Helvetica Neue Light', 'Helvetica Neue', Helvetica, Arial,
      'Lucida Grande', sans-serif;
    font-weight: 300;
    font-size: 14px;
    margin: 0;
    padding: 0;
  }
`

const App = () => (
  <Router>
    <React.Fragment>
      <Navbar />
      <Route
        path="/:url*"
        exact
        strict
        render={props => <Redirect to={`${props.location.pathname}/`} />}
      />
      <Switch>
        <Route path="/" component={Home} />
        <Route path="/video/:videoId" component={Video} />
        <Route component={NotFound} />
      </Switch>
    </React.Fragment>
  </Router>
)

export default hot(module)(App)
