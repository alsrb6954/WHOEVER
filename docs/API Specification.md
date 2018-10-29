# API Specification

## Common Headers

* `Authentication` : `Bearer ` + AUTH_TOKEN

## Error Response Body

| Name | Type | Description |
| ---- | ---- | ----------- |
| error | object | error object. client can recognize the error status through this field's existence. |
| message | string | **essential**. message string presented to user. |
| detail | string | **optional**. information string used for debugging. |

**Examples**
```
{
    "error": {
        "message": "Can't access that resource.",
        "detail": "KINDS_OF_ERROR_CODE_CAN_BE_HERE."
    }
}
```

## User

Registered user in WHOEVER.

[Get User Auth Token](APIs/Get User Auth Token) : `GET /api/v1/auth/kakao/login/`

[Invalidate User Auth Token](APIs/Invalidate User Auth Token) : `GET /api/v1/auth/logout/`

## Lesson

Register New Lesson.

[Create New Lesson](APIs/Create New Lesson) : `POST /api/v1/lessons/`

[Get List of Lessons](APIs/Get List of Lessons) : `GET /api/v1/lessons/`

[Get Detail of Lesson](APIs/Get Detail of Lesson) : `GET /api/v1/lessons/:lid/`

[Update Specific Lesson](APIs/Update Specific Lesson) : `PUT /api/v1/lessons/:lid/`

[Delete Specific Lesson](APIs/Delete Specific Lesson) : `DELETE /api/v1/lessons/:lid/`
