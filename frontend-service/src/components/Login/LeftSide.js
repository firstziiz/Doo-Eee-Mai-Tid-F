import React from 'react'
import styled from 'styled-components'

import passionImg from '../../../public/bg/passion.jpg'

const PhotoContainer = styled.div`
  height: 100vh;
  width: calc(100% - 520px);
  background: url('${passionImg}');
  background-size: cover;

  @media (max-width: 519px) {
    display: none;
  }
`

export default () => <PhotoContainer />
