package edu.project3;

public enum HttpStatus {
    OK(200),
    Created(201),
    Accepted(202),
    NotModified(304),
    Found(302),
    MultipleChoices(300),
    BadRequest(400),
    Unauthorized(401),
    PaymentRequired(402),
    Forbidden(403),
    NotFound(404),
    MethodNotAllowed(405),
    InternalServerError(500),
    NotImplemented(501),
    BadGateway(502),
    Unknown(0);

    final Integer code;

    HttpStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getStatus() {
        return this.name();
    }
}
