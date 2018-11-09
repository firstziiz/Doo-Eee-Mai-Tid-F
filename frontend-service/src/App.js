import React from 'react'
import { Router, Route, Switch, Redirect } from 'react-static'
import { createGlobalStyle } from 'styled-components'
import { hot } from 'react-hot-loader'

import Home from './containers/Home'
import Login from './containers/Login'
import Logout from './containers/Logout'
import Curriculum from './containers/Curriculum'
import Live from './containers/Live'
import Video from './containers/Video'
import Videos from './containers/Videos'
import NotFound from './containers/404'

createGlobalStyle`
  body {
    font-family: 'HelveticaNeue-Light', 'Helvetica Neue Light', 'Helvetica Neue', Helvetica, Arial,
      'Lucida Grande', sans-serif;
    font-weight: 300;
    font-size: 14px;
    margin: 0;
    padding: 0;
  }
  .ant-notification {
    z-index: 999;
  }
`

const App = () => (
  <Router>
    <React.Fragment>
      <Route
        path="/:url*"
        exact
        strict
        render={props => <Redirect to={`${props.location.pathname}/`} />}
      />
      <Switch>
        <Route path="/login" component={Login} />
        <Route path="/logout" component={Logout} />
        <Route exact path="/" component={Home} />
        <Route path="/curriculum" component={Curriculum} />
        <Route path="/videos" component={Videos} />
        <Route path="/live" component={Live} />
        <Route path="/subjects/:subjectID" component={Videos} />
        <Route path="/video/:videoId" component={Video} />
        <Route component={NotFound} />
      </Switch>
    </React.Fragment>
  </Router>
)

export default hot(module)(App)
