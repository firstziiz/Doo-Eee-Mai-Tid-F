import React from 'react'
import { Route, Switch, withRouter, Redirect } from 'react-static'
import { createGlobalStyle } from 'styled-components'
import { hot } from 'react-hot-loader'

import Home from './containers/Home'
import Login from './containers/Login'
import Logout from './containers/Logout'
import Curriculum from './containers/Curriculum'
import History from './containers/History'
import Video from './containers/Video'
import Videos from './containers/Videos'
import NotFound from './containers/404'
import { observer, inject } from 'mobx-react'

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

@inject('userStore')
@observer
class App extends React.Component {
  state = {
    loading: true
  }

  async componentDidMount() {
    this.props.userStore.setLoginIn(true)
    this.props.userStore
      .getProfile()
      .then(async () => {
        if (!this.props.userStore.authenticated) {
          await this.props.userStore.setLoginIn(false)
        } else {
          await this.props.userStore.setLoginIn(false)
        }
      })
      .catch(() => {
        console.log('you are not logging in')
      })
  }

  render() {
    if (this.props.userStore.logingIn) {
      return <div />
    }

    return (
      <React.Fragment>
        <Route
          path="/:url*"
          exact
          strict
          render={props => <Redirect to={`${props.location.pathname}/`} />}
        />
        <Switch>
          <Route key={'1'} exact path="/login" component={Login} />
          <Route key={'2'} exact path="/logout" component={Logout} />
          <Route key={'3'} exact path="/" component={Home} />
          <Route key={'4'} exact path="/curriculum" component={Curriculum} />
          <Route key={'5'} exact path="/videos" component={Videos} />
          <Route key={'6'} exact path="/history" component={History} />
          <Route key={'7'} exact path="/subjects/:subjectId" component={Videos} />
          <Route key={'8'} exact path="/video/:videoId" component={Video} />
          <Route key={'9'} component={NotFound} />
        </Switch>
      </React.Fragment>
    )
  }
}

export default hot(module)(withRouter(App))
