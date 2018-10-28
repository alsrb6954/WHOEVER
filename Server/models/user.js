var keystone = require('keystone');
var Types = keystone.Field.Types;

/**
 * User Model
 * ==========
 */
var User = new keystone.List('User');

User.add({
	name: { type: Types.Name, index: true },
	email: { type: Types.Email, unique: true, index: true },
	password: { type: Types.Password },
    w_user_id: { type: String, required: true, initial: true },
    w_user_email: { type: String, required: true, initial: true },
    w_user_profile: { type: String },
    w_eula_version: { type: String, required: true, initial: true },
    access_token: { type: String, unique:true, index: true, required: true, initial: true },
}, 'Permissions', {
	isAdmin: { type: Boolean, label: 'Can access Keystone', index: true },
});

// Provide access to Keystone
User.schema.virtual('canAccessKeystone').get(function () {
	return this.isAdmin;
});

/**
 * Relationships
 */
User.relationship({ path: 'tokens', ref: 'AuthToken', refPath: 'user_id'});

/**
 * Registration
 */
User.defaultColumns = 'name, email, isAdmin';
User.register();
