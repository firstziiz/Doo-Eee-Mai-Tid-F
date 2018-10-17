const express = require('express')
const path = require('path')
const app = express()

app.use(express.static('dist'))

app.use((req, res) => {
  res.status(404).sendFile(path.join(__dirname, 'dist' , '404.html'))
})

const PORT = process.env.PORT || 3000
app.listen(PORT, err => {
  if (err) throw err
  console.log('> Ready on port', PORT)
})