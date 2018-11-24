import api from '../utils/api'

const SUBJECT_URL = `https://subject-service-dot-sit-cloudnative.appspot.com`

const SubjectService = {
  getSubjectsByProgramId: programId => {
    return api.get(`${SUBJECT_URL}/program/${programId}/subjects`)
  },
  getPrograms: () => {
    return api.get(`${SUBJECT_URL}/programs`)
  }
}

export default SubjectService
