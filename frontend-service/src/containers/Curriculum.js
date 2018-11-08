import React from 'react'
import Layout from '../components/Core/Layout'
import { Link } from 'react-static'
import { Icon } from 'antd'

class Curriculum extends React.Component {
  render() {
    return (
      <Layout>
        <div className="row pt-2">
          <div className="col-5">
            <h3>Curriculum</h3>
            <div className="list-group mb-3">
              <a
                onClick={() => console.log('hi bro!')}
                className="list-group-item d-flex justify-content-between"
              >
                <div>
                  <h6 className="my-0">BSc. IT</h6>
                  <small className="text-muted">
                    Bachelor of Science Program in Information Technology
                  </small>
                </div>
                <span className="text-muted">
                  <Icon type="book" theme="outlined" className="mr-2" />
                </span>
              </a>
              <a
                onClick={() => console.log('hi bro!')}
                className="list-group-item d-flex justify-content-between"
              >
                <div>
                  <h6 className="my-0">BSc. CS</h6>
                  <small className="text-muted">
                    Bachelor of Science Program in Computer Science
                  </small>
                </div>
                <span className="text-muted">
                  <Icon type="book" theme="outlined" className="mr-2" />
                </span>
              </a>
              <a
                onClick={() => console.log('hi bro!')}
                className="list-group-item d-flex justify-content-between"
              >
                <div>
                  <h6 className="my-0">BSc. DSI</h6>
                  <small className="text-muted">Bachelor of Arts Digital Service Innovation</small>
                </div>
                <span className="text-muted">
                  <Icon type="book" theme="outlined" className="mr-2" />
                </span>
              </a>
            </div>
          </div>
          <div className="col-5">
            <h3>Subject of XXX</h3>
            <div className="list-group mb-3">
              <Link to="/subjects/1" className="list-group-item d-flex justify-content-between">
                <div>
                  <h6 className="my-0">INT101</h6>
                  <small className="text-muted">IT Fundamental</small>
                </div>
              </Link>
              <Link to="/subjects/2" className="list-group-item d-flex justify-content-between">
                <div>
                  <h6 className="my-0">INT102</h6>
                  <small className="text-muted">Computer Programming 1</small>
                </div>
              </Link>
              <Link to="/subjects/3" className="list-group-item d-flex justify-content-between">
                <div>
                  <h6 className="my-0">INT104</h6>
                  <small className="text-muted">
                    Discrete Mathematics for Information Technology
                  </small>
                </div>
              </Link>
              <Link to="/subjects/4" className="list-group-item d-flex justify-content-between">
                <div>
                  <h6 className="my-0">INT201</h6>
                  <small className="text-muted">Network I</small>
                </div>
              </Link>
              <Link to="/subjects/5" className="list-group-item d-flex justify-content-between">
                <div>
                  <h6 className="my-0">INT202</h6>
                  <small className="text-muted">Software Development Process I</small>
                </div>
              </Link>
            </div>
          </div>
        </div>
      </Layout>
    )
  }
}

export default Curriculum
