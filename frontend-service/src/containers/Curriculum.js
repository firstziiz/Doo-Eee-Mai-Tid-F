import React from 'react'
import Layout from '../components/Core/Layout'
import { Link } from 'react-static'
import { Icon } from 'antd'
import SubjectService from '../services/SubjectService'
import requireAuth from '../utils/requireAuth'
import Spinner from '../components/Spinner'

@requireAuth
class Curriculum extends React.Component {
  state = {
    programs: [],
    subjects: [],
    selectedProgram: '',
    programName: ''
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

      this.setState({
        subjects,
        selectedProgram: program_id,
        programCode: program_code
      })
    } catch (error) {}
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
                <div>
                  {this.state.programCode !== program.program_code ? (
                    <a
                      key={program.program_id}
                      onClick={() => this.onSelectProgram(program.program_id, program.program_code)}
                      className="list-group-item d-flex justify-content-between"
                    >
                      <div>
                        <h6 className="my-0">{program.program_code}</h6>
                        <small className="text-muted">{program.program_name}</small>
                      </div>
                      <span className="text-muted">
                        <Icon type="book" theme="outlined" className="mr-2" />
                      </span>
                    </a>
                  ) : (
                    <a
                      key={program.program_id}
                      onClick={() => this.onSelectProgram(program.program_id, program.program_code)}
                      className="list-group-item d-flex justify-content-between"
                      style={{ backgroundColor: '#000' }}
                    >
                      <div>
                        <h6 className="my-0" style={{ color: '#fff' }}>
                          {program.program_code}
                        </h6>
                        <small style={{ color: '#fff' }}>{program.program_name}</small>
                      </div>
                      <span className="text-muted">
                        <Icon
                          type="book"
                          theme="outlined"
                          className="mr-2"
                          style={{ color: '#fff' }}
                        />
                      </span>
                    </a>
                  )}
                </div>
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
