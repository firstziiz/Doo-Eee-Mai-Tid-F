import React from 'react'
import Layout from '../components/Core/Layout'
import { notification } from 'antd'
import ReactQuill from 'react-quill'
import ReactPlayer from 'react-player'
import axios from 'axios'
import moment from 'moment'
import 'react-quill/dist/quill.snow.css'

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

  componentDidMount() {
    const videoService = 'http://video-service-bold-grysbok.mybluemix.net/video/'
    axios({
      url: `${this.videoId}`,
      baseURL: videoService,
      method: 'get'
    })
      .then(response => {
        const video = response.data
        if (video) {
          this.setState({
            url: video.player.hls_url,
            name: video.video_name,
            date: moment(video.video_date).format('D MMM YYYY'),
            period: `${moment(video.video_starttime).format('HH:mm:ss')} - ${moment(video.video_endtime)}`,
            duration: video.video_duration
          })
        }
        return response.data
      })
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
        <div className="embed-responsive" >
          <ReactPlayer
            controls
            url={this.state.url}
          />
        </div>
        <div className="row py-3">
          <div className="col-4">
            <div className="detail">
              <h4 className="mb-0">{this.state.name}</h4>
              <h6 className="text-muted">99,999 Views</h6>
              <div className="text-muted">Duration: 9 mins</div>
              <div className="text-muted">Profressor: Dr. Kunchai Sodhom — INT101</div>
              <div className="text-muted">Date: 09 Nov 2561</div>
            </div>
          </div>
          <div className="col-8">
            <div className="notes">
              <ReactQuill value={this.state.note} onChange={this.handleChange} />
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
