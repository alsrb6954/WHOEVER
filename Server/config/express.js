module.exports = function() {
  var express = require('express');
  var session = require('express-session');
  var bodyParser = require('body-parser');
  var db = require('./db')();

  var app = express();

  app.set('views', './views');
  app.set('view engine', 'jade');

  app.use(bodyParser.urlencoded({extended: false}));
  app.use(session({
    secret : 'my fdgfhshgsfg',
    resave : false,
    saveUninitialized: true
  }));

  return app;
}
