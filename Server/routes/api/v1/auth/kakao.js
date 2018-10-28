var keystone = require('keystone');
var request = require('request');

var User = keystone.list('User');
var AuthToken = keystone.list('AuthToken');

exports = module.exports = function (req, res) {

    var headers = {
      'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
      'Authorization': 'Bearer ' + req.body.access_token
    }

    var options = {
        url: "https://kapi.kakao.com/v2/user/me",
        method: 'POST',
        headers: headers
    }

    request(options, function(error, response, body) {
        if (!error && response.statusCode == 200) {
            let jsonObj = JSON.parse(body);
            let newUser = new User.model({
                w_user_id: jsonObj.id,
                w_user_name: jsonObj.properties.nickname,
                w_user_email: null,
                w_user_profile: jsonObj.properties.profile_image,
                w_eula_version: req.body.w_eula_version,
                access_token: req.body.access_token
            })
            User.model.findOne({w_user_id: jsonObj.id}).exec(function(err, user) {
                if(err) return res.status(500).json({
                    error: {
                        message: "Server error.",
                        detail: "500. When find user in kakao auth."
                    }
                });
                if(!user) { // Register new User and create new AuthToken if kakao user is not registerd.
                    newUser.save(function(err, createdUser) {
                        if(err) {
                            console.log(err);
                            return res.status(500).json({
                                error: {
                                    message: "Server error.",
                                    detail: "500. When create new User."
                                }
                            });
                        }
                        let newAuthToken = new AuthToken.model({
                            user_id: createdUser._id
                        });
                        newAuthToken.save(function(err, created) {
                            if(err) {
                                console.log(err);
                                return res.status(500).json({
                                    error: {
                                        message: "Server error.",
                                        detail: "500. When create new AuthToken. User is not registerd."
                                    }
                                });
                            }
                            return res.status(200).json({w_token: created.w_token});
                        })
                    })
                } else { // If kakao user is already exist.
                    User.model.findOneAndUpdate({w_user_id: jsonObj.id}, {$set: {access_token: req.body.access_token}}).exec(function(err, result) {
                        if(err) return res.status(500).json({
                            error: {
                                message: "Server error.",
                                detail: "500. When update kakao access_token."
                            }
                        });
                    })
                    AuthToken.model.findOne({user_id: user._id}).exec(function(err, authToken) {
                        if(err) return res.status(500).json({
                            error: {
                                message: "Server error.",
                                detail: "500. When find auth token."
                            }
                        });
                        if(!authToken) {
                            let newAuthToken = new AuthToken.model({
                                user_id: user._id
                            });
                            newAuthToken.save(function(err, created) {
                                if(err) {
                                    console.log(err);
                                    return res.status(500).json({
                                        error: {
                                            message: "Server error.",
                                            detail: "500. When create new AuthToken. User is already registerd."
                                        }
                                    });
                                }
                                return res.status(200).json({w_token: created.w_token});
                            })
                        }
                        return res.status(200).json({w_token: authToken.w_token});
                    })
                }
            })
        } else if(error) {
            console.log("[error] : " + error);
            res.send(error);
        }
    });
};
