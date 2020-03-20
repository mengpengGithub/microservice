package com.captain.user.response;

/**
 * @ClassName LoginResponse
 * @Description TODO
 * @Author mengpeng
 * @Date 2020/3/16 21:01
 * @Version 1.0
 */
public class LoginResponse extends Response {

    private String token;


    public LoginResponse(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
