const newman = require('newman')

newman.run({
  collection: require('./e-learning.postman_collection.json'),
  reporters: 'cli',
  environment: require('./cloudnative.postman_environment.json')
}, function (err) {
  if (err) throw err
  console.log('collection run complete')
})