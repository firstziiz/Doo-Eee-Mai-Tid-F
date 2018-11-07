import React from 'react'
import Layout from '../components/Core/Layout'
import { notification } from 'antd'
import ReactQuill from 'react-quill'
import 'react-quill/dist/quill.snow.css'

class Video extends React.Component {
  state = {
    note: '... your note.'
  }

  handleNoteChange = value => {
    this.setState({ note: value })
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
        <div className="embed-responsive embed-responsive-16by9">
          <iframe
            className="embed-responsive-item"
            src="https://www.youtube.com/embed/zpOULjyy-n8?rel=0"
            allowFullscreen
          />
        </div>
        <div className="row py-3">
          <div className="col-4">
            <div className="detail">
              <h4 className="mb-0">INT999 Makeup Class</h4>
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
