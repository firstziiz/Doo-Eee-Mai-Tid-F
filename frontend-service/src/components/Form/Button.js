import React from 'react'
import { Button } from 'antd'
import styled from 'styled-components'

const StyledButton = styled(Button)`
  height: 50px;
  background-color: #000;
  color: #fff !important;

  &:before {
    display: none !important;
  }

  &:active {
  }

  &:hover,
  :focus {
    background-color: #000;
    border: none;
  }

  & h6 {
    color: #fff;
  }
`

export default props => (
  <StyledButton
    className={props.className}
    loading={props.loading}
    onClick={props.onClick}
    htmlType={props.type}
    block={props.block}
  >
    {!props.loading && <h6 className="m-0">{props.title}</h6>}
  </StyledButton>
)
