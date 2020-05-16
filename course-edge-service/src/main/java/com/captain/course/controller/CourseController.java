package com.captain.course.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.captain.course.dto.CourseDTO;
import com.captain.course.service.ICourseService;
import com.captain.thrift.user.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName CourseController
 * @Description TODO
 * @Author mengpeng
 * @Date 2020/5/7 23:35
 * @Version 1.0
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Reference
    private ICourseService iCourseService;

    @ResponseBody
    @RequestMapping(value = "/courseList", method = RequestMethod.GET)
    public List<CourseDTO> courseList(HttpServletRequest request) {

        UserDTO user = (UserDTO) request.getAttribute("user");
        System.out.println(user);
        return iCourseService.courseList();
    }
}
