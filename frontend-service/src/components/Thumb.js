import React from 'react'
import styled from 'styled-components'
import { Link } from 'react-static'
import moment from 'moment'

const ThumbContainer = styled(Link)`
  position: relative;
  display: block;
  margin: 5px;
`

const ThumbDate = styled.div`
  position: absolute;
  top: 20px;
  left: 0;
  width: 100px;
  background: rgba(0,0,0,0.7);
  padding: 5px 10px;
  color: #fff;
  text-align: center;
  span {
    display: block;
  }
`

const ThumbBox = styled.div`
  overflow: hidden; 
`

const Thumbnail = styled.img`
  transition: all 0.3s ease;
  :hover {
    transform: scale(1.1);
  }
`

const ThumbTitle = styled.div`
  background: #666;
  padding: 10px 15px;
  color: #fff;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
`

const Thumb = ({ src, title, date, link }) => {
  const time = moment(date)
  const day = time.format('DD')
  const month = time.format('MMMM')
  return (
    <ThumbContainer to={link}>
      <ThumbBox>
        <Thumbnail src={src} />
      </ThumbBox>
      <ThumbDate>
        <span>{day}</span>
        <small>{month}</small>
      </ThumbDate>
      <ThumbTitle>{title}</ThumbTitle>
    </ThumbContainer>
  )
}

export default Thumb