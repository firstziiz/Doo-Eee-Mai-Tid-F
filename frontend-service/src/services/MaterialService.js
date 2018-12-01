import api from '../utils/api'

const MATERIAL_SERVICE_URL = 'https://material-service-dot-sit-cloudnative.appspot.com'

const SubjectService = {
  upload: (subjectId, data) => {
    return api.post(`${MATERIAL_SERVICE_URL}/subject/${subjectId}/materials`, data)
  },
  getMaterialsBySubjectId: subjectId => {
    return api.get(`${MATERIAL_SERVICE_URL}/subject/${subjectId}/materials`)
  }
}

export default SubjectService
