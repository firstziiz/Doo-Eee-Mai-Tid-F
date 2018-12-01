import React, { Component } from 'react'
import logo from './logo.svg'
import './App.css'

import { Row, Col, Badge } from 'antd'

import axios from 'axios'

import config from './config'
import { Exception } from 'handlebars';

class App extends Component {
  state = {
    services: [],
    date: ''
  }

  constructor(props) {
    super(props)
    this.state = {
      services: [
        {
          name: 'Video Service',
          url: config.VIDEO_SERVICE,
          status: undefined,
          cpu: undefined
        },
        {
          name: 'Video History Service',
          url: config.VIDEO_HISTORY_SERVICE,
          status: undefined,
          cpu: undefined
        },
        {
          name: 'Material Service',
          url: config.MATERIAL_SERVICE,
          status: undefined,
          cpu: undefined
        },
        {
          name: 'Subject Service',
          url: config.SUBJECT_SERVICE,
          status: undefined,
          cpu: undefined
        },
        {
          name: 'Authentication Service',
          url: config.AUTHENTICATION_SERVICE,
          status: undefined,
          cpu: undefined
        }
      ]
    }
  }

  async componentDidMount() {
    const date = new Date().toString()
    this.setState({date})
    let services = this.state.services.slice()
    services.map( async service => {
      try{
        const {data} = await axios.get(service.url+'/health')
        if(data.status === 'UP' || data.status === 'DOWN') {
          service.status = data.status
        }else {
          throw new Exception()
        }
        const {data: cpu} = await axios.get(service.url+'/metrics/process.cpu.usage')
        service.cpu = cpu.measurements[0].value
        console.log(cpu)
      }catch(err){
        console.log(err)
        service.status = 'DOWN'
      }
      this.setState({services})
    })
  }

  render() {
    return (
      <div className="App">
        <div style={{backgroundColor: '#282c34', height: '100vh', color: '#FFF'}}>
          <h1 style={{textAlign: 'center', color:'#FFF'}}>
            Doo-Eee-Mai-Tid-F Services Status
          </h1>
          {this.state.services.map( service => {
            return (
              <Row type="flex" justify="center" style={{marginBottom: '5px'}} gutter={0}>
                <Col xs={12}>
                  <Row type="flex" justify="end"  gutter={0}>
                    <Badge dot={true} title={service.status || 'Checking'} count={`${service.status || 'Checking'}`} style={service.status==='UP' ? { backgroundColor: '#52c41a', top:'9px' } : service.status===undefined ? {backgroundColor: '#ffc41a', top:'9px' } : {top:'9px'}} />
                  </Row>
                </Col>
                <Col xs={12}>
                  <Row gutter={0}>
                    <Col xs={24} style={{textAlign: 'left', paddingLeft:'15px'}}>
                      <a style={{color:'#FFF'}} href={service.url} target='_blank'>{service.name}</a> {service.cpu && `(CPU Usage: ${service.cpu*1000/10.0}%)`}
                    </Col>
                  </Row>
                </Col>
              </Row>
            )
          })}
          <div style={{marginTop: '15px'}}>
            {this.state.date}
          </div>
        </div>
      </div>
    );
  }
}

export default App;
