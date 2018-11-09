import React from 'react'
import ReactDOM from 'react-dom'
import 'antd/dist/antd.css'
// Your top level component
import App from './App'
import { Provider } from 'mobx-react'
import store from './store'

// Export your top level component as JSX (for static rendering)
export default App

// Render your app
if (typeof document !== 'undefined') {
  const renderMethod = module.hot ? ReactDOM.render : ReactDOM.hydrate || ReactDOM.render
  const render = Comp => {
    renderMethod(
      <Provider {...store}>
        <Comp />
      </Provider>,
      document.getElementById('root'))
  }

  // Render!
  render(App)
}
