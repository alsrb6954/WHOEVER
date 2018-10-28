var keystone = require('keystone');

var AuthToken = keystone.list('AuthToken');

// Invalidate User
exports.invalidate = function(req, res) {

    AuthToken.model.findOne({w_token: req.headers.bearer}).remove().exec(function(err, result) {
        if(err) return res.status(500).json({
            error: {
                message: "Server error.",
                detail: "500. When remove AuthToken for logout."
            }
        });
        return res.status(200).json({ result: "ok" });
    });
}
