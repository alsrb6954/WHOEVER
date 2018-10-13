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
            res.send(error);
        }
    });
};
