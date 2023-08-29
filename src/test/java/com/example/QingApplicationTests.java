package com.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.*;
import com.example.model.entity.Address;
import com.example.model.vo.AddressVO;
import com.example.service.LinkTaskService;
import com.example.service.WxPayOwnService;
import com.example.service.WxuserService;
import com.example.utils.AddressUtil;
import com.example.utils.DateUtils;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.qiniu.util.Json;
import com.qiniu.util.StringMap;


import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.service.impl.WxPayOwnServiceImpl.generateRandomNumber;

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
    @Resource
    WxPayService wxService;
    @Resource
    WxPayOwnService wxPayOwnService;
    @Resource
    ProductMapper productMapper;
    @Resource
    AddressUtil addressUtil;




    @Test
    void contextLoads() throws WxPayException, JSONException {
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
//
//        System.out.println(provinceMapper.getAddressCode("44","18","03","102000"));


//        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
//        orderRequest.setBody("测试");
//        orderRequest.setTotalFee(BaseWxPayRequest.yuanToFen(String.valueOf(0.01)));//元转成分
//        orderRequest.setOpenid("oc58G4yyADYi48awvlVL_VXMNLGY");
//        orderRequest.setTimeStart("20230814215000");
//        orderRequest.setTimeExpire("20230814230000");
//        orderRequest.setOutTradeNo("12345600aa0");
//        orderRequest.setNotifyUrl("8.130.43.168");
//        orderRequest.setProductId("200304078888877aaa6");
//        orderRequest.setTradeType("JSAPI");//这个是商户的参数
//        orderRequest.setSpbillCreateIp("8.130.43.168");

       // String order = wxService.createOrder(orderRequest).toString();

//        WxPayMpOrderResult wxPayMpOrderResult = wxService.createOrder(orderRequest);
//
//        System.out.println("我是响应的数据");
//        System.out.println(wxPayMpOrderResult);

//        JSONObject jsonObj = new JSONObject(order); // 将JSON字符串转换为JSONObject
//        System.out.println(jsonObj);





//        return null;
//        } catch (Exception e) {
////            log.error("微信支付失败！订单号：{},原因:{}", orderNo, e.getMessage());
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//
//        return null;
//    }


//        WxPayOrderQueryResult wxPayOrderQueryResult = wxService.queryOrder(null, "20030407");
//        WxPayOrderQueryResult x = wxPayOrderQueryResult;
//        System.out.println(x);

//        wxService.parseRefundNotifyResult()
//        wxService.queryOrder(null, "1233211234567");

//        Date date = new Date();
//
//        System.out.println(DateUtils.dateAddTime(date,23));
      //  System.out.println(wxPayOwnService.createOrder("1121111"));

    }
    @Test
    void test() throws WxPayException {
//        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
//
//        orderRequest.setBody("轻小南商城商品");
//
//        orderRequest.setTotalFee(BaseWxPayRequest.yuanToFen(String.valueOf(0.01)));//元转成分
//
//        orderRequest.setOpenid("iujiuiuiui");
//
//        orderRequest.setSpbillCreateIp("0.0.0.0");//用户的客户端ip地址
//
//        Date date = new Date();
//
//        String startTime = DateUtils.dateToString(date);
//        String endTime=DateUtils.dateToString(DateUtils.dateAddTime(date,24));
//        orderRequest.setTimeStart(startTime);
//        orderRequest.setTimeExpire(endTime);
//
//        String outTradeNo= generateRandomNumber();
//        orderRequest.setOutTradeNo(outTradeNo);
//
//        orderRequest.setNotifyUrl("xiaoligongzuopshi.com");
//
//        orderRequest.setProductId("88888888");
//
//        orderRequest.setTradeType("Wap");//这个是商户的参数
//
//        WxPayMpOrderResult wxPayMpOrderResult=wxService.createOrder(orderRequest);
//
//        System.out.println(wxPayMpOrderResult);

//        wxuserMapper.getOneByOpenidWxuser("")
//        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
//
//        addressQueryWrapper.eq("creator", "6b8e2699-75d2-4022-b82c-4217178011ba");
//
//        List<Address> addresses = addressMapper.selectList(addressQueryWrapper);
//
//        List<AddressVO> addressVOS = new ArrayList<>();
//
//        for (Address address : addresses) {
//            AddressVO addressVO = new AddressVO();
//            BeanUtils.copyProperties(address, addressVO);
//            addressVOS.add(addressVO);
//        }
//
//        System.out.println(addressVOS);
//
//        System.out.println(addresses);


//        System.out.println(productMapper.getBuysByUserId("916e3f82-f9c0-4976-8adf-f4bef9f3ad7b"));
//        System.out.println(addressUtil.getRegionName("441803"));

        String productid="product:181a0754-8c77-4c12-8ae3-8a9853562697";
        System.out.println(wxPayOwnService.refund(productid));
        //System.out.println(wxPayOwnService.queryOrder(productid));
    }
}
