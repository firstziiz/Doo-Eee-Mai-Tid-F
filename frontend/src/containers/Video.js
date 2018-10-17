import React, { Component } from 'react'
import ReactPlayer from 'react-player'
import {
  Layout,
  Breadcrumb,
  Row,
  Col,
  Card,
  Spin
} from 'antd'
import axios from 'axios'
import styled from 'styled-components'
import moment from 'moment'

import NotFound from './404'

const { Content, Footer } = Layout

const TagCard = styled.div`
  padding: 10px;
  background: #108DB8;
  color: #fff;
  border-radius: 3px;
  margin: 0 5px;
  font-family: "Microsoft YaHei", BlinkMacSystemFont, sans-serif;
`

const CustomContent = styled.div`
  background: #fff; 
  padding: 15px;
  min-height: 80vh;
`

const Container = styled.div`
  text-align: ${props => props.center && 'center'};
  margin-top: ${props => props.mt}px;
`


class Video extends Component {
  state = {
    // video detail
    videoName: '',
    url: '',
    date: '',
    period: '',

    404: false,
    loading: true,
    error: false,
    errorMessage: '',
    errorText: '',
  }

  getVideo = () => {
    this.setState({ loading: true, error: false })
    const videoId = this.props.match.params.videoId
    const baseUrl = process.env.VIDEO_API
    axios.get(`${baseUrl}/video/${videoId}`)
      .then(({data}) => {
        const date = moment(data.video_date)
        const start = moment(data.video_starttime)
        const end = moment(data.video_endtime)
        if (data) {
          this.setState({
            url: data.player.hls_url,
            videoName: data.video_name,
            loading: false,
            date: date.format('DD MMM YYYY'),
            period: `${start.format('HH:mm')} - ${end.format('HH:mm')}`
          })
          console.log(data)
        } else {
          this.setState({ 404: true, loading: false })
        }
      })
      .catch(err => {
        console.error(err)
        this.setState({
          loading: false,
          error: true,
          errorMessage: err.message,
          errorText: err.toString()
        })
        this.getVideo()
      })
  }

  async componentDidMount() {
    this.getVideo()
  }

  renderError = () => {
    return (
      <div>
        <h1>Error - {this.state.errorMessage}</h1>
        {this.state.errorText}
      </div>
    )
  }

  render() {
    if (this.state.loading) return <Container center mt="80"><Spin size="large" /></Container>
    if (this.state.error) return this.renderError()
    if (this.state[404]) return <NotFound />
    return (
        <Layout className="layout">
          <Content style={{ padding: '0 15px' }}>
            <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>B.Sc.IT</Breadcrumb.Item>
              <Breadcrumb.Item>INT xxx</Breadcrumb.Item>
              <Breadcrumb.Item>{this.state.videoName}</Breadcrumb.Item>
            </Breadcrumb>
            <CustomContent>
              <Row type="flex" justify="center">
                <Col xs={24} md={12}>
                  <ReactPlayer
                    url={this.state.url}
                    controls
                    style={{
                      background: '#000',
                      borderRadius: '5px'
                    }}
                    width="100%"
                  />
                  <h3>{this.state.videoName}</h3>
                  <Row>
                    <Col xs={12} md={8} gutter={16}>
                      <TagCard>{this.state.date}</TagCard>
                    </Col>
                    <Col xs={12} md={8} gutter={16}>
                      <TagCard>{this.state.period}</TagCard>
                    </Col>
                  </Row>
                </Col>
              </Row>
            </CustomContent>
          </Content>
          <Footer style={{ textAlign: 'center' }}>
            DEMTF ©2018 Created by Alchemist-Lazy
          </Footer>
        </Layout>
    )
  }
}

export default Video