import React, { Component } from 'react'
import ReactPlayer from 'react-player'
import { Layout, Breadcrumb, Row, Col } from 'antd'
import axios from 'axios'
import NotFound from './404'


const { Content, Footer } = Layout


class Video extends Component {
  state = {
    url: '',
    videoData: null,
    videoName: '',
    date: '',
    period: '',
    404: false,
    isLoaded: false
  }

  async componentDidMount() {
    const path = this.props.match.params[0]
    const baseUrl = 'http://localhost:8080'
    const { data } = await axios.get(`${baseUrl}${path}`)
    if (data) {
      this.setState({
        url: data.player.hls_url,
        videoData: data,
        videoName: data.video_name,
        isLoaded: true
      })
      console.log(data)
    } else {
      this.setState({ 404: true, isLoaded: true })
    }
  }

  render() {
    if (!this.state.isLoaded) return null
    if (this.state[404]) return <NotFound />
    return (
        <Layout className="layout">
          <Content style={{ padding: '0 50px' }}>
            <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>B.Sc.IT</Breadcrumb.Item>
              <Breadcrumb.Item>INT xxx</Breadcrumb.Item>
              <Breadcrumb.Item>{this.state.videoName}</Breadcrumb.Item>
            </Breadcrumb>
            <div style={{ background: '#fff', padding: 24, minHeight: 280 }}>
            <Row>
              <Col span={12} offset={6}>
                <ReactPlayer
                  url={this.state.url}
                  controls
                  width="100%"
                />
                <h2>{this.state.videoName}</h2> 
              </Col>
            </Row>
            </div>
          </Content>
          <Footer style={{ textAlign: 'center' }}>
            DEMTF ©2018 Created by Alchemist-Lazy
          </Footer>
        </Layout>
    )
  }
}

export default Video