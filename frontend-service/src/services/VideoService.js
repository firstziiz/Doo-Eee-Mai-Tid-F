import api from '../utils/api'

const VIDEO_URL = `https://video-service-dot-sit-cloudnative.appspot.com`

const VideoService = {
  getVideosBySubjectId: subjectId => {
    return api.get(`${VIDEO_URL}/subject/${subjectId}/videos`)
  },
  getVideo: videoId => {
    return api.get(`${VIDEO_URL}/video/${videoId}`)
  }
}

export default VideoService
