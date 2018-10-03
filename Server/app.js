var app = require('./config/express')();
// var User = require('./models/user');

var auth = require('./routes/auth');
app.use('/auth', auth);

app.get('/', function(req, res) {
  console.log('fd')
  res.send('fd')
})

app.listen(3000, function() {
  console.log('Connected, 3000 port!');
});
