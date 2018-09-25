var app = require('./config/express')();
// var User = require('./models/user');
var passport = require('passport');
var KakaoStrategy = require('passport-kakao').Strategy;
var request = require('request');

app.get('/', function(req, res) {
  getKakaoUserInfo('EkywDqcSv6iv-fH_DLqMEQwTLH3wmlyBrUoRjAo8BRIAAAFmDDl4mg');
  res.send('Hello ' + JSON.stringify(req.session));
});

app.get('/users', function(req, req) {
  res.send('hi');
})

app.listen(3000, function() {
  console.log('Connected, 3000 port!');
});

// function requestAccessToken(code) {
//   if (code != null) {
//   var kakaoRestKey = "my code";
//   var redirect_uri = "my web url";
//
//   var headers = {
//     'User-Agent': 'Super Agent/0.0.1',
//     'Content-Type': 'application/x-www-form-urlencoded'
//   }
//
//   var options = {
//     url: "https://kauth.kakao.com/oauth/token",
//     method: 'POST',
//     header: headers,
//     form: {
//       grant_type: "authorization_code",
//       client_id: kakaoRestKey,
//       redirect_uri: redirect_uri,
//       code: code
//     }
//   }
//
//   request(options, function(error, response, body) {
//     var jsonObj;
//     if (!error && response.statusCode == 200) {
//         jsonObj = JSON.parse(body);
//         getUserInfo(jsonObj.access_token);
//
//     } else if(error) {
//         consoleLog("[error] : " + error);
//     }
//
//   });
// }

function getKakaoUserInfo(access_token) {
  var headers = {
  'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
  'Authorization': 'Bearer ' + access_token
  }

  var options = {
    url: "https://kapi.kakao.com/v2/user/me",
    method: 'GET',
    headers: headers
  }

  request(options, function(error, response, body) {
    if (!error && response.statusCode == 200) {
      var jsonObj = JSON.parse(body);
      console.log(jsonObj);
    } else if(error) {
      console.log("[error] : " + error);
    }
  });
}

function getGoogleToken(access_token) {
  var headers = {
    'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
  }

  var options = {
    url: "https://www.googleapis.com/plus/v1/people/me?access_token={access_token}",
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
