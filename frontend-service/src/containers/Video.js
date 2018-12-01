import React from 'react'
import Layout from '../components/Core/Layout'
import { notification } from 'antd'
import ReactPlayer from 'react-player'
import axios from 'axios'
import moment from 'moment'
import 'react-quill/dist/quill.snow.css'

import { videoServiceURL, videoHistoryServiceURL } from '../utils/env'
import requireAuth from '../utils/requireAuth'
import NotFound from '../containers/404'
import VideoService from '../services/VideoService'

@requireAuth
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

    await axios({
      url: `/video_histories`,
      baseURL: videoHistoryServiceURL,
      method: 'post',
      data
    })
  }

  async componentDidMount() {
    const response = await VideoService.getVideo(this.videoId)

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

  saveNote = async () => {
    await axios({
      url: `/notes`,
      baseURL: videoServiceURL,
      method: 'post',
      body: {
        userId: 1,
        videoId: this.videoId,
        content: this.state.note
      }
    }).then(resp => {
      notification['success']({
        message: 'Note Saved',
        description: 'Yes! Bros. I have already saved your note for this video.'
      })
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
              <div className="text-muted">Date: {this.state.date}</div>
              <div className="text-muted">
                Time: {this.state.starttime} - {this.state.endtime}
              </div>
            </div>
          </div>
          <div className="col-8">
            <div className="notes">
              <textarea
                value={this.state.note}
                onChange={e => this.handleNoteChange(e.target.value)}
                rows="5"
                style={{ width: '100%', borderColor: '#ccc' }}
              />
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
