import React from 'react'
import styled from 'styled-components'
import { Link } from 'react-static'
import { Icon } from 'antd'

const SidebarStyled = styled.nav`
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  z-index: 100; /* Behind the navbar */
  padding: 48px 0 0; /* Height of navbar */
  box-shadow: inset -1px 0 0 rgba(0, 0, 0, 0.1);

  .sidebar-sticky {
    position: relative;
    top: 0;
    height: calc(100vh - 48px);
    padding-top: 0.5rem;
    overflow-x: hidden;
    overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
  }

  @supports ((position: -webkit-sticky) or (position: sticky)) {
    .sidebar-sticky {
      position: -webkit-sticky;
      position: sticky;
    }
  }

  .nav-link {
    display: flex;
    align-items: center;
    font-weight: 500;
    color: #333;
  }

  .nav-link .feather {
    margin-right: 4px;
    color: #999;
  }

  .nav-link.active {
    color: #007bff;
  }

  .nav-link:hover .feather,
  .nav-link.active .feather {
    color: inherit;
  }

  .sidebar-heading {
    font-size: 0.75rem;
    text-transform: uppercase;
  }
`

class Sidebar extends React.Component {
  render() {
    return (
      <SidebarStyled className="col-md-2 d-none d-md-block bg-light sidebar">
        <div className="sidebar-sticky">
          <ul className="nav flex-column">
            <li className="nav-item">
              <Link className="nav-link" exact to="/">
                <Icon type="home" theme="outlined" className="mr-2" />
                Home <span className="sr-only">(current)</span>
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/curriculum">
                <Icon type="book" theme="outlined" className="mr-2" />
                Curriculum
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/videos">
                <Icon type="youtube" theme="outlined" className="mr-2" />
                All Videos
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/live">
                <Icon type="notification" theme="outlined" className="mr-2" />
                Live
              </Link>
            </li>
          </ul>
          <h6 className="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
            <span>Favorites Subject!</span>
          </h6>
          <ul className="nav flex-column mb-2">
            <li className="nav-item">
              <Link className="nav-link" to="/subjects/1">
                <Icon type="read" theme="outlined" className="mr-2" />
                INT999 Motorcycle Training
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/subjects/2">
                <Icon type="read" theme="outlined" className="mr-2" />
                INT999 Cooking
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/subjects/3">
                <Icon type="read" theme="outlined" className="mr-2" />
                INT999 Introduction of Ragnarok M
              </Link>
            </li>
          </ul>
        </div>
      </SidebarStyled>
    )
  }
}

export default Sidebar
