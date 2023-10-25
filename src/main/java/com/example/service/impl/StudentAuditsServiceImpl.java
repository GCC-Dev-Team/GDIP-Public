package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.StudentAudits;
import com.example.service.StudentAuditsService;
import com.example.mapper.StudentAuditsMapper;
import org.springframework.stereotype.Service;

/**
* @author L
* @description 针对表【student_audits】的数据库操作Service实现
* @createDate 2023-10-14 23:51:41
*/
@Service
public class StudentAuditsServiceImpl extends ServiceImpl<StudentAuditsMapper, StudentAudits>
    implements StudentAuditsService{

}




