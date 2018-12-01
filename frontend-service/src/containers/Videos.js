import React from 'react'
import { Row, Icon, Table, Col, Card } from 'antd'
import Layout from '../components/Core/Layout'
import { Link } from 'react-static'
import moment from 'moment'
import VideoService from '../services/VideoService'
import requireAuth from '../utils/requireAuth'
import SubjectService from '../services/SubjectService'
import MaterialService from '../services/MaterialService'
import Spinner from '../components/Spinner'
import { inject, observer } from 'mobx-react'

const UploadPanel = props => (
  <Row>
    <Col span={24}>
      <Card title="New file">
        <Row type="flex" gutter={32}>
          <Col span={6}>
            <p>Select file</p>
          </Col>
        </Row>
        <Row gutter={32}>
          <Col span={6}>
            <div className="form-group">
              <input type="file" className="form-control-file" onChange={e => props.onChange(e)} />
            </div>
          </Col>
          <Col>
            <button type="submit" className="btn btn-primary mb-2" onClick={props.uploadMaterial}>
              Upload
            </button>
          </Col>
        </Row>
      </Card>
    </Col>
  </Row>
)

@observer
@inject('userStore')
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
    const materials = await MaterialService.getMaterialsBySubjectId(this.subjectId).then(
      resp => resp.data
    )

    this.setState({
      subject,
      videos,
      materials
    })
  }

  uploadMaterial = async () => {
    const data = new FormData()
    data.append('file', this.state.material)
    data.append('isActive', true)

    return MaterialService.upload(this.subjectId, data)
  }

  renderUploadPanel = () => {
    if (this.props.userStore.user.role === 'TEACHER') {
      return (
        <UploadPanel
          onChange={e =>
            this.setState({
              material: e.target.files[0]
            })
          }
          uploadMaterial={this.uploadMaterial}
        />
      )
    }
  }

  render() {
    // if (this.state.videos.length === 0) {
    //   return (
    //     <Layout>
    //       <Row type="flex" justify="center" align="middle" style={{ height: '100vh' }}>
    //         <Spinner />
    //       </Row>
    //     </Layout>
    //   )
    // }

    const columns = [
      {
        title: '#',
        dataIndex: '#',
        key: '#',
        width: 10,
        render: (text, record, index) => (
          <Row type="flex" align="middle">
            <p style={{ margin: 0 }}>{index + 1}</p>
          </Row>
        )
      },
      {
        title: 'Material',
        dataIndex: 'fileName',
        key: 'fileName',
        render: (text, record) => (
          <Row type="flex" align="middle">
            <Icon type="file" style={{ marginRight: 10 }} />
            <a href={record.path}>{text}</a>
          </Row>
        )
      },
      {
        title: 'Last updated',
        dataIndex: 'updatedAt',
        key: 'updatedAt'
      }
    ]

    return (
      <Layout>
        <div>
          <h1>
            {this.subjectId && this.state.subject
              ? `${this.state.subject.subject_code || ''}: ${this.state.subject.subject_name || ''}`
              : 'All Videos'}
          </h1>
          <h3>Materials</h3>

          {this.renderUploadPanel()}

          <Row style={{ margin: '16px 0' }}>
            <Col span={24}>
              <Table columns={columns} dataSource={this.state.materials} pagination={false} />
            </Col>
          </Row>
          <h3>Videos</h3>
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
              <div className="col-12">ไม่มี Videos สำหรับวิชานี้</div>
            )}
          </div>
        </div>
      </Layout>
    )
  }
}

export default Videos
