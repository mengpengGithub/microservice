package com.captain.user.controller;

import com.captain.thrift.user.UserInfo;
import com.captain.user.dto.UserDTO;
import com.captain.user.redis.RedisClient;
import com.captain.user.response.LoginResponse;
import com.captain.user.response.Response;
import com.captain.user.thrift.ServiceProvider;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.Random;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author mengpeng
 * @Date 2020/3/15 23:16
 * @Version 1.0
 */
@RestController
public class UserController {

    @Autowired
    private ServiceProvider serviceProvider;

    @Autowired
    private RedisClient redisClient;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestParam("username") String username
            , @RequestParam("password") String password) {

        //1.验证用户名密码
        UserInfo userInfo = null;
        try {
            userInfo = serviceProvider.getUserService().getUserByName(username);
        } catch (TException e) {
            e.printStackTrace();
            return Response.USER_PASSWORD_INVALID;
        }
        if (userInfo == null) {
            return Response.USER_PASSWORD_INVALID;
        }

        if (!userInfo.getPassword().equalsIgnoreCase(md5(password))) {
            return Response.USER_PASSWORD_INVALID;
        }

        //2.生成token
        String token = genToken();
        //3.缓存用户
        redisClient.set(token, toDTO(userInfo), 3600);

        return new LoginResponse(token);
    }


    @RequestMapping(value = "/sendVerifyCode", method = RequestMethod.POST)
    public Response sendVerifyCode(String mobile, String email) {

        String message = "Verify code is:";
        String code = randomCode("0123456789", 6);
        try {
            boolean result = false;
            if (StringUtils.isNotBlank(mobile)) {
                result = serviceProvider.getMessageService().sendMobileMessage(
                        mobile, message + code);

                redisClient.set(mobile, code);
            } else if (StringUtils.isNotBlank(email)) {
                result = serviceProvider.getMessageService().sendEmailMessage(
                        email, message + code);
                redisClient.set(email, code);
            } else {
                return Response.MOBILE_OR_EMAIL_REQUIRED;
            }

            if (!result) {
                return Response.SEND_VERIFYCODE_FAILED;
            }
        } catch (TException e) {
            return Response.exception(e);
        }
        return Response.SUCCESS;
    }


    public Response register(String username, String password
            , String mobile, String email, String verifyCode) {

        if (StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {
            return Response.MOBILE_OR_EMAIL_REQUIRED;
        }

        if (StringUtils.isNotBlank(mobile)) {
            String redisCode = redisClient.get(mobile);
            if (!StringUtils.equals(verifyCode, redisCode)) {
                return Response.VERIFY_CODE_INVALID;
            }
        }else {
            String redisCode = redisClient.get(email);
            if (!StringUtils.equals(verifyCode, redisCode)) {
                return Response.VERIFY_CODE_INVALID;
            }
        }


        return null;
    }


    private Object toDTO(UserInfo userInfo) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userInfo, userDTO);
        return userDTO;

    }

    private String genToken() {
        return randomCode("0123456789abcdefghijklmnopqrstuvwxyz", 32);
    }

    private String randomCode(String s, int size) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int loc = random.nextInt(s.length());
            char c = s.charAt(loc);
            result.append(c);
        }
        return result.toString();
    }

    private String md5(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(password.getBytes("utf-8"));

            return HexUtils.toHexString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
