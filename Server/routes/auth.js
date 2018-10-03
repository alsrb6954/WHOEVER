var express = require('express');
var route = express.Router();
var request = require('request');

route.post('/kakao', function(req, res) {
  var wAccess_token = req.body.wAccess_token;

  var headers = {
  'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
  'Authorization': 'Bearer ' + wAccess_token
  }

  var options = {
    url: "https://kapi.kakao.com/v2/user/me",
    method: 'POST',
    headers: headers
  }

  request(options, function(error, response, body) {
    if (!error && response.statusCode == 200) {
      var jsonObj = JSON.parse(body);
      var wUser_id = jsonObj.id;
      var wUser_name = jsonObj.properties.nickname;
      var wUser_email = 'kakao@test.com';
      var wUser_profileImageUrl = jsonObj.properties.profile_image;
      obj = { "wUser_id": wUser_id, "wUser_name": wUser_name, "wUser_email": wUser_email, "wUser_profileImageUrl": wUser_profileImageUrl };
      console.log(obj)
      res.send(obj);
    } else if(error) {
      console.log("[error] : " + error);
    }
  });
});

route.post('/google', function(req, res) {
  // var wAccess_token = req.body.wAccess_token;
  getGoogleUserInfo('fdfd');
  res.send('google');
});

function getGoogleUserInfo(access_token) {
  console.log(access_token);
  var headers = {
    'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
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
}

module.exports = route;
