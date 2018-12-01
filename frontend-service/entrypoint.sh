#!/bin/sh
set -e

echo "window.env = {" >> dist/env.js
echo "  authenticationServiceURL: \"$AUTHENTICATION_SERVICE_URL\"," >> dist/env.js
echo "  subjectServiceURL: \"$SUBJECT_SERVICE_URL\"," >> dist/env.js
echo "  videoServiceURL: \"$VIDEO_SERVICE_URL\"," >> dist/env.js
echo "  videoHistoryServiceURL: \"$VIDEO_HISTORY_SERVICE_URL\"," >> dist/env.js
echo "  materialServiceURL: \"$MATERIAL_SERVICE_URL\"" >> dist/env.js
echo "}" >> dist/env.js

exec "$@"