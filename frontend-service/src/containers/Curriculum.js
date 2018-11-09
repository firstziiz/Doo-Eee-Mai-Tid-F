import React from 'react'
import Layout from '../components/Core/Layout'
import { Link } from 'react-static'
import { Icon } from 'antd'

import axios from '../utils/axios-creator'
import { subjectServiceURL } from '../utils/env'

class Curriculum extends React.Component {
  state = {
    subjects: []
  }

  async componentWillMount() {
    const response = await axios({
      method: 'get',
      url: `${subjectServiceURL}/subjects`
    })

    this.setState({
      subjects: response.data
    })
  }

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
              {this.state.subjects.map((sj, index) => (
                <Link
                  key={index}
                  to={`/subjects/${sj.subject_id}`}
                  className="list-group-item d-flex justify-content-between"
                >
                  <div>
                    <h6 className="my-0">{sj.subject_code}</h6>
                    <small className="text-muted">{sj.subject_name}</small>
                  </div>
                </Link>
              ))}
            </div>
          </div>
        </div>
      </Layout>
    )
  }
}

export default Curriculum
