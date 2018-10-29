# Get User Authentication Token

Request user authentication token used for calling server APIs.
If user is registered and valid user, server will return the Bearer authentication token.

* URL : `/api/v1/auth/kakao/login`
* Method : `POST`

### Request Header

No header is required.

### Request Body Parameters

| Name | Type | Description |
| ---- | ---- | ----------- |
| access_token | string | **required**. Token which was retrieved from kakao authentication process. This token is kinds of authentication secret, so should be saved safely. |

### Response Body

| Name | Type | Description |
| ---- | ---- | ----------- |
| w_token | string | WHOEVER authentication token, if succeeded. |
| w_user_id | string | . |
| w_user_name | string | . |
| w_user_profile | string | . |

### Examples

```text
// When user requests.
GET /api/v1/auth/kakao/login/?access_token=ABCDEF

{
    "w_token": "WHOEVER_AUTH_TOKEN_STRING",
    "w_user_id": "123123",
    "w_user_name": "후에버",
    "w_user_profile": "http://fdfdddd"
}
```
