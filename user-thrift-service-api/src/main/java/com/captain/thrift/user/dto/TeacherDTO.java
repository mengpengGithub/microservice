package com.captain.thrift.user.dto;

import com.captain.thrift.user.UserInfo;

/**
 * @ClassName TeacherDTO
 * @Description TODO
 * @Author mengpeng
 * @Date 2020/5/5 20:56
 * @Version 1.0
 */
public class TeacherDTO extends UserDTO {

    private String intro;

    private int stars;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
