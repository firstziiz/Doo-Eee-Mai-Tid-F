import React from 'react'
import Layout from '../components/Core/Layout'
import { Link } from 'react-static'
import moment from 'moment'
import VideoService from '../services/VideoService'

class History extends React.Component {
  state = {
    videos: []
  }

  async componentDidMount() {
    const videos = await VideoService.getVideoHistories().then(resp => resp.data)
    console.log(videos)
  }

  render() {
    return (
      <Layout>
        <h1>Video History</h1>
        <div className="row">
          {this.state.videos.length !== 0 &&
            this.state.videos.map((video, index) => (
              <Link className="col-4" key={index} to={`/video/${video.video_id}`}>
                <div className="card mb-4 shadow-sm">
                  <img src={video.video_thumbnail} className="card-img-top" />
                  <div className="card-body">
                    <p className="card-text text-muted">{video.video_name}</p>
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
          {this.state.videos.length === 0 && (
            <div className="col-12">คุณยังไม่มีประวัติการเข้าชม</div>
          )}
        </div>
      </Layout>
    )
  }
}

export default History
