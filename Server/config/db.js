module.exports = function() {
  var mongoose = require('mongoose');
  var credentials = require('../lib/credentials.js');
  mongoose.connect('mongodb://' + credentials);

  var db = mongoose.connection;
  db.on('error', console.error);
  db.once('open', function(){
    // CONNECTED TO MONGODB SERVER
    console.log("Connected to mongod server");
  });

  return db;
}
