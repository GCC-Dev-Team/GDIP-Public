package com.example.service;

import javax.validation.constraints.NotNull;

public interface CourseService {
    //获取我的所有课程（）(和控制器对接的)

    String getMyCourse();

    //更新所有人的课程


    /**
     * 获取我的课程信息，现在的（主要是新绑定的用户，因为后续都是手动刷新）；
     *
     * @param schoolId
     * @param password
     * @return
     */
    String getCourseAllByPost(@NotNull String schoolId, @NotNull String password);
}
