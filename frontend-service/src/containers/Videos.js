import React from 'react'
import styled from 'styled-components'
import Layout from '../components/Core/Layout'
import { Link } from 'react-static'
import moment from 'moment'
import VideoService from '../services/VideoService'

// import axios from '../utils/axios-creator'
// import { videoServiceURL } from '../utils/env'

const TitleOverflow = styled.p``
class Videos extends React.Component {
  state = {
    videos: []
  }

  get subjectId() {
    return this.props.match.params.subjectId
  }

  async componentWillMount() {
    const videos = await VideoService.getVideosBySubjectId(this.subjectId).then(resp => resp.data)

    this.setState({
      videos
    })
  }

  render() {
    return (
      <Layout>
        <div>
          <h1>{this.subjectId ? `Subject ${this.subjectId}` : 'All Videos'}</h1>
          <Link className="row">
            {this.state.videos &&
              this.state.videos.map((video, index) => (
                <Link className="col-4" key={index}>
                  <Link to={`/video/${video.video_id}`}>
                    <div className="card mb-4 shadow-sm">
                      <img src={video.video_thumbnail} className="card-img-top" />
                      <div className="card-body">
                        <TitleOverflow className="card-text text-muted">
                          {video.video_name}
                        </TitleOverflow>
                        <div className="d-flex flex-column">
                          <small className="text-muted">{video.teacher.teacher_name}</small>
                          <small className="text-muted">
                            {moment(video.video_date).format('DD MMM YY')}
                          </small>
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
