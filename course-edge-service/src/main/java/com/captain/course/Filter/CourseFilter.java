package com.captain.course.Filter;

import com.captain.thrift.user.dto.UserDTO;
import com.captain.user.client.LoginFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName CourseFilter
 * @Description TODO
 * @Author mengpeng
 * @Date 2020/5/7 23:42
 * @Version 1.0
 */
public class CourseFilter extends LoginFilter {
    @Override
    protected void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO) {
        request.setAttribute("user", userDTO);
    }
}
