import React from 'react'
import styled from 'styled-components'
import Layout from '../components/Core/Layout'
import { Link } from 'react-static'
import { Icon, notification } from 'antd'
import SubjectService from '../services/SubjectService'
import requireAuth from '../utils/requireAuth'
import Spinner from '../components/Spinner'

const FavIcon = styled.div`
  font-size: 1.4rem;
`

@requireAuth
class Curriculum extends React.Component {
  state = {
    programs: [],
    subjects: [],
    selectedProgram: '',
    programName: '',
    favSubjects: []
  }

  async componentWillMount() {
    try {
      const programs = await SubjectService.getPrograms().then(resp => resp.data)
      this.setState({
        programs
      })
    } catch (error) {}
  }

  onSelectProgram = async (program_id, program_code) => {
    try {
      const subjects = await SubjectService.getSubjectsByProgramId(program_id).then(
        resp => resp.data
      )

      const favSubjects = await SubjectService.getFavoriteSubjects().then(resp => resp.data)

      this.setState({
        subjects,
        favSubjects,
        selectedProgram: program_id,
        programCode: program_code
      })

      await this.getFavoritesSubject()
    } catch (error) {}
  }

  addFavoriteSubject = async (e, subjectId) => {
    e.preventDefault()

    await SubjectService.addFavoriteSubject(subjectId)
      .then(_ => {
        notification['success']({
          message: 'เพิ่มวิชาโปรดสำเร็จ',
          description: 'คุณได้เพิ่มวิชาโปรดสำเร็จแล้ว'
        })
      })
      .catch(_ => {
        notification['error']({
          message: 'เพิ่มวิชาโปรดสำเร็จ',
          description: 'คุณไม่สามารถเพิ่มวิชาโปรดได้ กรุณาติดต่อผู้ดูแลระบบ'
        })
      })
  }

  render() {
    return (
      <Layout>
        <div className="row pt-2">
          <div className="col-5">
            <h3>Curriculum</h3>
            <div className="list-group mb-3">
              {this.state.programs.length === 0 && <Spinner />}
              {this.state.programs.map(program => (
                <a
                  key={program.program_id}
                  className="list-group-item d-flex justify-content-between"
                  style={{
                    background: this.state.programCode === program.program_code ? '#000' : '#fff'
                  }}
                  onClick={() => this.onSelectProgram(program.program_id, program.program_code)}
                >
                  <div>
                    <h6
                      className="my-0"
                      style={{
                        color: this.state.programCode === program.program_code ? '#fff' : '#000'
                      }}
                    >
                      {program.program_code}
                    </h6>
                    <small className="text-muted">{program.program_name}</small>
                  </div>
                </a>
              ))}
            </div>
          </div>
          <div className="col-5">
            <h3>Subject of {this.state.programCode}</h3>
            <div className="list-group mb-3">
              {this.state.subjects.map((sj, index) => (
                <Link
                  key={index}
                  to={`/subjects/${sj.subject_id}`}
                  className="list-group-item d-flex justify-content-between"
                >
                  <div>
                    <h6 className="my-0">{sj.subject_code}</h6>
                    <small className="text-muted">{sj.subject_name}</small>
                  </div>
                  <FavIcon
                    className="text-muted d-flex justify-content-center align-items-center"
                    onClick={e => this.addFavoriteSubject(e, sj.subject_id)}
                  >
                    <Icon
                      type="book"
                      theme={
                        this.state.favSubjects.find(f => f.subject_id + '' === sj.subject_id)
                          ? 'filled'
                          : 'outlined'
                      }
                      className="mr-2"
                    />
                  </FavIcon>
                </Link>
              ))}
            </div>
          </div>
        </div>
      </Layout>
    )
  }
}

export default Curriculum
