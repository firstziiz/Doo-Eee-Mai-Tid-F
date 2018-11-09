import React from 'react'
import styled from 'styled-components'

const StyledInput = styled.input`
  height: 50px;

  &:focus {
    box-shadow: none;
    border-color: #000;
  }
`

export default props => (
  <StyledInput
    type={props.type}
    className={`form-control ${props.className}`}
    onChange={props.onChange}
  />
)
