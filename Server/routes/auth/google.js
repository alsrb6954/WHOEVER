var keystone = require('keystone');
var request = require('request');

exports = module.exports = function (req, res) {

    var wAccess_token = req.body.wAccess_token;
    console.log(wAccess_token);

    var headers = {
      'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
      'Authorization': 'Bearer ' + wAccess_token
    }

    var options = {
        // url :"https://www.googleapis.com/plus/v1/people/me"
        // url: "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token={accces_token}",
        // url: "https://www.googleapis.com/plus/v1/people/me?access_token="+access_token,
        url: "https://www.googleapis.com/plus/v1/people/me?key="+AIzaSyDzxorTYO__7EUv8CIEnG1cjEmXJtORhNw,
        method: 'GET',
        headers: headers
      }

    request(options, function(err, response, body) {
    if(!err && response.statusCode == 200) {
      console.log(JSON.parse(body));
    } else if(err) {
      console.log(err);
    }
    })
};
