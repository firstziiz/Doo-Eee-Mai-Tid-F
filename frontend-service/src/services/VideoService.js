import api from '../utils/api'

const VIDEO_URL =
  window.env.videoServiceURL || `https://video-service-dot-sit-cloudnative.appspot.com`
const VIDEO_HISTORY_URL =
  window.env.videoHistoryServiceURL ||
  `https://video-history-service-dot-sit-cloudnative.appspot.com`

const VideoService = {
  getVideosBySubjectId: subjectId => {
    return api.get(`${VIDEO_URL}/subject/${subjectId}/videos`)
  },
  getVideo: videoId => {
    return api.get(`${VIDEO_URL}/video/${videoId}`)
  },
  createVideoHistory: videoId => {},
  getVideoHistories: () => {
    return api.get(`${VIDEO_HISTORY_URL}/users/video-history`)
  }
}

export default VideoService
