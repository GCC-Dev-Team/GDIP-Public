package com.example;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.*;
import com.example.model.entity.Product;
import com.example.model.entity.Task;
import com.example.model.entity.Wxuser;
import com.example.model.vo.ProvinceVo;
import com.example.service.LinkTaskService;
import com.example.service.WxuserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    FavoritesMapper favoritesMapper;

    @Resource
    FollowersMapper followersMapper;
    @Resource
    AddressMapper addressMapper;

    @Resource
    ProvinceMapper provinceMapper;

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
//        System.out.println(task.getPeople());


       // System.out.printf(wxuserMapper.selectPage(new Page<Wxuser>(2, 2), new QueryWrapper<Wxuser>()).getRecords().toString());
//        Page<Wxuser> wxuserPage = wxuserMapper.selectPage(new Page<Wxuser>(2, 2), new QueryWrapper<Wxuser>());
//
//        PageVO pageVO = new PageVO(wxuserPage.getRecords(),wxuserPage.getTotal(),wxuserPage.getSize(),wxuserPage.getCurrent());
//
//        System.out.println(pageVO);


        //System.out.println(qiniuProperties);
//
//        IPage<Product> myFavoritesByUserId = favoritesMapper.getMyFavoritesByUserId("2002",new Page<>(1,2));
//        System.out.println(myFavoritesByUserId.getRecords());//分页查询//分页底层源码就是这个page

//        IPage myAllFollowers = followersMapper.getMyAllFollowers("6b8e2699-75d2-4022-b82c-4217178011ba", new Page<>(1, 1));
//
//        System.out.println(myAllFollowers.getRecords());
//        IPage<ProvinceVo> allProvinces = addressMapper.getAllProvinces(new Page<ProvinceVo>(1, 20));
//
//        List<ProvinceVo> records = allProvinces.getRecords();
//
//        System.out.println(records);
      //  System.out.println(provinceMapper.getAllProvinces(new Page<>(, 20)).getRecords());

        System.out.println(provinceMapper.getAddressCode("44","18","03","102000"));

    }

}
