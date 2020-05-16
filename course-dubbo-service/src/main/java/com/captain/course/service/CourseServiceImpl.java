package com.captain.course.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.captain.course.dto.CourseDTO;
import com.captain.course.mapper.CourseMapper;
import com.captain.course.thrift.ServiceProvider;
import com.captain.thrift.user.UserInfo;
import com.captain.thrift.user.dto.TeacherDTO;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName CourseServiceImpl
 * @Description TODO
 * @Author mengpeng
 * @Date 2020/5/7 22:56
 * @Version 1.0
 */
@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ServiceProvider serviceProvider;
    @Override
    public List<CourseDTO> courseList() {

        List<CourseDTO> courseDTOS = courseMapper.listCourse();
        if(courseDTOS != null){
            for (CourseDTO courseDTO : courseDTOS) {
                Integer teacherId = courseMapper.getCourseTeacher(courseDTO.getId());
                if(teacherId != null){
                    try {
                        UserInfo userInfo = serviceProvider.getUserService().getTeacherById(teacherId);
                        courseDTO.setTeacher(trans2Teacher(userInfo));
                    } catch (TException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }

        return courseDTOS;
    }

    private TeacherDTO trans2Teacher(UserInfo userInfo) {
        TeacherDTO dto = new TeacherDTO();
        BeanUtils.copyProperties(userInfo, dto);
        return dto;
    }
}
