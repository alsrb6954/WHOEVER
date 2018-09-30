var app = require('./config/express')();
// var User = require('./models/user');

var auth = require('./routes/auth')(app);
app.use('/auth', auth);

// custom 404 page
app.use(function(req, res){
        res.type('text/plain');
        res.status(404);
        res.send('404 - Not Found');
});

// custom 500 page
app.use(function(err, req, res, next){
        console.error(err.stack);
        res.type('text/plain');
        res.status(500);
        res.send('500 - Server Error');
});

app.listen(3000, function() {
  console.log('Connected, 3000 port!');
});
