import React from 'react'
import styled from 'styled-components'
import Layout from '../components/Core/Layout'
import { Link } from 'react-static'

const TitleOverflow = styled.p`
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
`
class Videos extends React.Component {
  render() {
    return (
      <Layout>
        <div>
          <h1>All Videos</h1>
          <Link className="row">
            {[...Array(12)].map((video, index) => (
              <Link className="col-3" key={index}>
                <Link to="/video/1">
                  <div className="card mb-4 shadow-sm">
                    <img src="https://placeimg.com/200/125/any" className="card-img-top" />
                    <div className="card-body">
                      <TitleOverflow className="card-text text-muted" >INT999: Make Up Class</TitleOverflow>
                      <div className="d-flex flex-column">
                        <small className="text-muted">9 mins</small>
                        <small className="text-muted">Dr. Kunchai Sodhom — INT101</small>
                        <small className="text-muted">09 Nov 2561</small>
                      </div>
                    </div>
                  </div>
                </Link>
              </Link>
            ))}
          </Link>
        </div>
      </Layout>
    )
  }
}

export default Videos
