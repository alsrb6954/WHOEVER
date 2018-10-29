# Invalidate User Authentication Token

Invalidate already-issued WHOEVER authentication token. If user wants to logout explicitly, this API can be called.

* URL : `/api/v1/auth/logout/`
* Method : `GET`

### Request Header

Follows the common header rule.

### Request Body Parameters

No Parameters.

### Response Body

| Name | Type | Description |
| ---- | ---- | ----------- |
| result | string | **essential**. The result of request. `ok`, if succeeded. If some error occurred, error message will be returned. |

### Examples

```text
GET /api/v1/auth/logout/

{
    "result": "ok"
}
```
