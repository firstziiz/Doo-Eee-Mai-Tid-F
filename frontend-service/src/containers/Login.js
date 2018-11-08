import React from 'react'
import Layout from '../components/Core/Layout'

class Login extends React.Component {
  render() {
    return (
      <div>
        <div className="container text-center pt-5">
          <div className="row">
            <div className="col-6 offset-3">
              <div className="card">
                <div className="card-body">
                  <form action="#">
                    <h3>
                      SITFLIX <small className="d-block">Next Generation of E-learning</small>
                    </h3>
                    <div className="form-group text-left">
                      <label htmlFor="">Student ID:</label>
                      <input type="text" className="form-control" />
                    </div>
                    <div className="form-group text-left">
                      <label htmlFor="">Password:</label>
                      <input type="password" className="form-control" />
                    </div>
                    <button className="btn btn-primary btn-block">Login</button>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default Login
