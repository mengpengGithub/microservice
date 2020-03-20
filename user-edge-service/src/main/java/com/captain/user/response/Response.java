package com.captain.user.response;

/**
 * @ClassName Response
 * @Description TODO
 * @Author mengpeng
 * @Date 2020/3/15 23:30
 * @Version 1.0
 */
public class Response {

    public static final Response USER_PASSWORD_INVALID = new Response("1001"
            , "username or password is error");
    public static final Response MOBILE_OR_EMAIL_REQUIRED = new Response("1002"
            , "mobile or email is required");
    public static final Response SEND_VERIFYCODE_FAILED = new Response("1003"
            , "send verify code failed");
    public static final Response SUCCESS = new Response();
    public static final Response VERIFY_CODE_INVALID = new Response("1004", "verify code invalid");
    private String code;

    private String message;

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Response exception(Exception e) {
        return new Response("9999", e.getMessage());
    }

    public Response() {
        this.code = "0";
        message = "success";
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
