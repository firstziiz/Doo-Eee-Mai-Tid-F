import api from '../utils/api'

let SUBJECT_URL = `https://subject-service-dot-sit-cloudnative.appspot.com`

if (typeof window !== 'undefined') {
  SUBJECT_URL =
    window.env.subjectServiceURL || `https://subject-service-dot-sit-cloudnative.appspot.com`
}

const SubjectService = {
  getSubjectsByProgramId: programId => {
    return api.get(`${SUBJECT_URL}/program/${programId}/subjects`)
  },
  getPrograms: () => {
    return api.get(`${SUBJECT_URL}/programs`)
  },
  getSubject: subjectId => {
    return api.get(`${SUBJECT_URL}/subject/${subjectId}`)
  },
  getFavoriteSubjects: () => {
    return api.get(`${SUBJECT_URL}/subject_favorites`)
  },
  addFavoriteSubject: subjectId => {
    const data = new FormData()
    data.append('subjectId', subjectId)

    return api.post(`${SUBJECT_URL}/subject_favorites`, data)
  }
}

export default SubjectService
