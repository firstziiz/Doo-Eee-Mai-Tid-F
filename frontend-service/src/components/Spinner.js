import React from 'react'
import { Spin, Icon } from 'antd'

const antIcon = <Icon type="loading" style={{ fontSize: 48 }} spin />;

export default (props) => ( <Spin indicator={antIcon} /> )