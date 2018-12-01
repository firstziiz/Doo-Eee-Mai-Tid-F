import api from '../utils/api'

let VIDEO_URL = `https://video-service-dot-sit-cloudnative.appspot.com`

if (typeof window !== 'undefined') {
  VIDEO_URL = window.env.videoServiceURL
}

let VIDEO_HISTORY_URL = `https://video-history-service-dot-sit-cloudnative.appspot.com`

if (typeof window !== 'undefined') {
  VIDEO_HISTORY_URL = window.env.videoHistoryServiceURL
}

const VideoService = {
  getVideosBySubjectId: subjectId => {
    return api.get(`${VIDEO_URL}/subject/${subjectId}/videos`)
  },
  getVideo: videoId => {
    return api.get(`${VIDEO_URL}/video/${videoId}`)
  },
  createVideoHistory: videoId => {
    return api.post(`${VIDEO_HISTORY_URL}/users/video-history`, { videoId })
  },
  getVideoHistories: () => {
    return api.get(`${VIDEO_HISTORY_URL}/users/video-history`)
  }
}

export default VideoService
