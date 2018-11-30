import React from 'react'
import styled from 'styled-components'
import { Row } from 'antd'
import Layout from '../components/Core/Layout'
import { Link } from 'react-static'
import moment from 'moment'
import VideoService from '../services/VideoService'
import requireAuth from '../utils/requireAuth'
import SubjectService from '../services/SubjectService'
import Spinner from '../components/Spinner'

@requireAuth
class Videos extends React.Component {
  state = {
    subject: null,
    videos: []
  }

  get subjectId() {
    return this.props.match.params.subjectId
  }

  async componentWillMount() {
    const subject = await SubjectService.getSubject(this.subjectId).then(resp => resp.data)
    const videos = await VideoService.getVideosBySubjectId(this.subjectId).then(resp => resp.data)

    this.setState({
      subject,
      videos
    })
  }

  render() {

    if (this.state.videos.length === 0) {
      return (
        <Layout>
          <Row type="flex" justify="center" align="middle" style={{ height: '100vh'}}>
            <Spinner />
          </Row>
        </Layout>
      )
    }

    return (
      <Layout>
        <div>
          <h1>
            {this.subjectId && this.state.subject
              ? `${this.state.subject.subject_code || ''}: ${this.state.subject.subject_name || ''}`
              : 'All Videos'}
          </h1>
          <div className="row">
            {this.state.videos &&
              this.state.videos.map((video, index) => (
                <Link className="col-4" key={index} to={`/video/${video.video_id}`}>
                  <div className="card mb-4 shadow-sm">
                    <img src={video.video_thumbnail} className="card-img-top" />
                    <div className="card-body">
                      <p className="card-text text-muted">
                        {video.video_name}
                      </p>
                      <div className="d-flex flex-column">
                        <small className="text-muted">{video.teacher.teacher_name}</small>
                        <small className="text-muted">
                          {moment(video.video_date).format('DD MMM YY')}
                        </small>
                      </div>
                    </div>
                  </div>
                </Link>
              ))}
          </div>
        </div>
      </Layout>
    )
  }
}

export default Videos
