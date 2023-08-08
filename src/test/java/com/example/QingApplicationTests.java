package com.example;

import com.example.mapper.LinkTaskMapper;
import com.example.mapper.TaskMapper;
import com.example.mapper.WxuserMapper;
import com.example.service.LinkTaskService;
import com.example.service.WxuserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class QingApplicationTests {
    @Resource
    WxuserService wxuserService;
    @Resource
    WxuserMapper wxuserMapper;
    @Resource
    LinkTaskService linkTaskService;

    @Resource
    LinkTaskMapper linkTaskMapper;
    @Resource
    TaskMapper taskMapper;

    @Test
    void contextLoads() {
//        //System.out.println(wxuserService.getById("qqq"));
//       // System.out.println(wxuserMapper.getOneByOpenidWxuser("oc58G4yuVeMwfUiIXp__a4D3YJb4"));
//        String fileNameToDelete = "小璇.png";
//        String[] fileNameList = {fileNameToDelete};
//        QiniuUtil.QiniuCloudDeleteImage(fileNameList);
//        System.out.printf( UUID.randomUUID().toString().substring(0, 9));

      // System.out.printf(String.valueOf(linkTaskService.getMyNoSingOutTask("3").size()));
//
//        Task task = taskMapper.selectById("1");
//        System.out.printf(task.getPrice().toString());

       // System.out.printf(wxuserMapper.selectPage(new Page<Wxuser>(2, 2), new QueryWrapper<Wxuser>()).getRecords().toString());
//        Page<Wxuser> wxuserPage = wxuserMapper.selectPage(new Page<Wxuser>(2, 2), new QueryWrapper<Wxuser>());
//
//        PageVO pageVO = new PageVO(wxuserPage.getRecords(),wxuserPage.getTotal(),wxuserPage.getSize(),wxuserPage.getCurrent());
//
//        System.out.println(pageVO);


        //System.out.println(qiniuProperties);

    }

}
