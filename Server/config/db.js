module.exports = function() {
  var mongoose = require('mongoose');
  mongoose.connect('mongodb://localhost/test');
  
  var db = mongoose.connection;
  db.on('error', console.error);
  db.once('open', function(){
    // CONNECTED TO MONGODB SERVER
    console.log("Connected to mongod server");
  });

  return db;
}
