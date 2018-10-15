import React from 'react'
import { withSiteData, Link } from 'react-static'
import { Row, Col, Layout } from 'antd'
import Thumb from '../components/Thumb'
import logoImg from '../logo.png'

import videos from './videos.json'

export default withSiteData(() => (
  <div>
    <h1 style={{ textAlign: 'center' }}>Welcome to Doo-Eee-Mai-Tid-F</h1>
    <Layout className="content" breakpoint='md'>
      <Row>
        {videos.map(video => (
          <Col span={4} key={video.video_id} gutter="24">
            <Thumb
              src={video.video_thumbnail}
              link={`/video/${video.video_id}`}
              date={video.video_date}
              title={video.video_name}
            />
          </Col>
        ))}
      </Row>
    </Layout>
  </div>
))
