import React from 'react'
import Layout from '../components/Core/Layout'
import { notification } from 'antd'
// import ReactQuill from 'react-quill'
import ReactPlayer from 'react-player'
import axios from 'axios'
import moment from 'moment'
import 'react-quill/dist/quill.snow.css'

import { videoServiceURL, videoHistoryServiceURL } from '../utils/env'

class Video extends React.Component {
  state = {
    note: '... your note.',
    url: '',
    name: '-',
    date: '-',
    duration: ''
  }

  handleNoteChange = value => {
    this.setState({ note: value })
  }
  get videoId() {
    return this.props.match.params.videoId
  }

  updateVideoHistory = async () => {
    const data = new FormData()
    data.append('user_id', '1')
    data.append('video_id', this.videoId)
    data.append('timestamp', moment(new Date()).format('x'))

    const response = await axios({
      url: `/video_histories`,
      baseURL: videoHistoryServiceURL,
      method: 'post',
      data
    })

    console.log(response)
  }

  async componentDidMount() {
    const response = await axios({
      url: `/video/${this.videoId}`,
      baseURL: videoServiceURL,
      method: 'get'
    })

    const video = response.data
    const endtime = moment(video.video_endtime).format('HH:mm')
    const starttime = moment(video.video_starttime).format('HH:mm')

    if (video) {
      this.setState({
        url: video.player.hls_url,
        name: video.video_name,
        date: moment(video.video_date).format('D MMM YYYY'),
        starttime,
        endtime,
        period: `${moment(video.video_starttime).format('HH:mm:ss')} - ${moment(
          video.video_endtime
        )}`
      })

      this.updateVideoHistory()
    }

    return response.data
  }

  saveNote = () => {
    notification['success']({
      message: 'Note Saved',
      description: 'Yes! Bros. I have already saved your note for this video.'
    })
  }
  render() {
    return (
      <Layout>
        <div className="embed-responsive">
          <ReactPlayer controls url={this.state.url} />
        </div>
        <div className="row py-3">
          <div className="col-4">
            <div className="detail">
              <h4 className="mb-0">{this.state.name}</h4>
              {/* <h6 className="text-muted">99,999 Views</h6> */}
              {/* <div className="text-muted">Duration:  mins</div> */}
              {/* <div className="text-muted">Profressor: Dr. Kunchai Sodhom — INT101</div> */}
              <div className="text-muted">Date: {this.state.date}</div>
              <div className="text-muted">
                Time: {this.state.starttime} - {this.state.endtime}
              </div>
            </div>
          </div>
          <div className="col-8">
            <div className="notes">
              {/* <ReactQuill value={this.state.note} onChange={this.handleChange} /> */}
              <div className="text-right">
                <button className="btn btn-primary btn-sm mt-2" onClick={() => this.saveNote()}>
                  Save Note
                </button>
              </div>
            </div>
          </div>
        </div>
      </Layout>
    )
  }
}

export default Video
