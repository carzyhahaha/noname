package com.noname.service.impl;

import com.noname.entity.Classroom;
import com.noname.mapper.ClassroomMapper;
import com.noname.service.ClassroomService;
import com.noname.vo.ClassroomVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl extends BaseServiceImpl<ClassroomMapper, Classroom> implements ClassroomService {

    public List<ClassroomVO> getClassroomVO() {
        return dao.getClassroomVO();
    }
}
