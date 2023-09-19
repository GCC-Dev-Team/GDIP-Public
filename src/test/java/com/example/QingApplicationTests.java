package com.example;

import com.example.config.CallbackAddressProperties;
import com.example.config.QiniuProperties;
import com.example.mapper.*;
import com.example.model.entity.Course;
import com.example.model.entity.Task;
import com.example.repository.CourseRepository;
import com.example.service.*;
import com.example.utils.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;


import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.client.RestTemplate;

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

    @Resource
    FavoritesMapper favoritesMapper;

    @Resource
    FollowersMapper followersMapper;
    @Resource
    AddressMapper addressMapper;

    @Resource
    ProvinceMapper provinceMapper;
    @Resource
    WxPayService wxPayService;
    @Resource
    ProductPayOwnService productPayOwnService;
    @Resource
    ProductMapper productMapper;
    @Resource
    AddressUtil addressUtil;

    @Resource
    FavoritesService favoritesService;

    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    RestTemplate restTemplate;

    @Resource
    AccountJudgmentService accountJudgmentService;
    @Resource
    CallbackAddressProperties callbackAddressProperties;

    @Resource
    QiniuProperties qiniuProperties;




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
      //  System.out.println(productPayOwnService.createOrder("1121111"));

    }
    @Test
    void test() throws WxPayException {


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

//        String productid="product:181a0754-8c77-4c12-8ae3-8a9853562697";
//        System.out.println(productPayOwnService.refund(productid));
        //System.out.println(productPayOwnService.queryOrder(productid));
//        System.out.println(favoritesService.judgeFavorite("1", "1"));

//        String s = TokenUtils.generateJWT("20030515", "123456");
//
//        String openid = TokenUtils.getOpenid(s);
//
//        System.out.println(openid);



      //  boolean tokenExpired = TokenUtils.isTokenExpired("eyJhbGciOiJIUzI1NiJ9.eyJvcGVuSWQiOiIyMDAzMDUxNSIsInJlZGlzVG9rZW4iOiIxMjM0NTYiLCJleHAiOjE2OTM0OTg5Mzl9.GwFUSO1zVFPnt_e5jcwyypePMWf6CCEgoDEvCwc2hmM");

        //System.out.println(tokenExpired);


//        System.out.println("我在这");
//        System.out.println(s);

//        String regionName = addressUtil.getRegionName("88882013");
//
//        System.out.println(regionName);

//        String name="xiaohu:"+ RandomUtil.generateRandomString(10);
//
//        String na="xiaoxuan:"+RandomUtil.generateRandomNumberString(12);
//        boolean b = name.contains("xiaohu:");
//        System.out.println(b);
//
//        if (name.contains("xiaohu:")){
//            System.out.println(name);
//        }
//
//        if(na.endsWith("xiaoxuan:")){
//
//            System.out.println(na);
//        }
//        stringRedisTemplate.opsForValue().set(RandomUtil.generateRandomString(16),"T:"+RandomUtil.generateRandomString(16));

//        System.out.println(addressUtil.getRegionName("6814"));
    }
    @Resource
    RedisToken redis;

    @Test
    void testRest(){
//        // 定义目标 API 的 URL
//        String apiUrl = "http://cuuemo.cn:5000/login";
//
//        // 构建 HTTP 请求头
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // 构建 JSON 请求体
//        String jsonBody = "{\n" +
//                "    \"loginid\": \"2022233201315\",\n" +
//                "    \"password\":\"20040627\"\n" +
//                "}";
//
//        // 创建 HTTP 请求实体，将 JSON 请求体添加到请求中
//        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
//
//        // 发送 POST 请求并接收响应
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, String.class);
//
//        // 获取响应体
//        String response = responseEntity.getBody();
//
//        System.out.println(response);
//
//        HttpStatus statusCode = responseEntity.getStatusCode();
//
//        System.out.println("wo");
//
//        System.out.println(statusCode);
//
//        if (statusCode.value()==200){
//            System.out.println("我就");
//        }
    }

    @Test
    void testService(){


//        String jwt="eyJhbGciOiJIUzI1NiJ9.eyJvcGVuSWQiOiJvYzU4RzR5eUFEWWk0OGF3dmxWTF9WWE1OTEdZIiwicmVkaXNUb2tlbiI6IjI2MWVlM2M2Y2I2NTQ0NWIiLCJleHAiOjE2OTUwNTQzMDB9.YZ6rqD_D69SpPMv_6-CrPwOgInogFay_g6YwfBusBgY";

//        String jwt="eyJhbGciOiJIUzI1NiJ9.eyJvcGVuSWQiOiJvYzU4RzR5dVZlTXdmVWlJWHBfX2E0RDNZSmI0IiwicmVkaXNUb2tlbiI6IjMzYTk0YWNhYTAzNjRlYWYiLCJleHAiOjE2OTQ5NjQ5MDR9.pcF8vuXGHtTLgBD9nP9Mrk0NWp_1dzLTArZslnEUgiA";
//
//        String openid = TokenUtils.getOpenid(jwt);
//
//        String redisToken = TokenUtils.getRedisToken(jwt);
//
//        boolean tokenExpired = TokenUtils.isTokenExpired(jwt);
//
//        System.out.println(tokenExpired+openid+redisToken);
//
//        String openId = redis.getOpenId(jwt);
//        System.out.println("openid:"+openId);
        //   System.out.println(accountJudgmentService.judgeIsAccount("2022233201315", "20040627"));

//        IPage<Task> myAllOrder = linkTaskMapper.getMyAllOrder("6b8e2699-75d2-4022-b82c-4217178011ba", new Page<>(1, 20));
//
//        System.out.println(myAllOrder);
//
//        System.out.println(myAllOrder.getRecords());
//
//        System.out.println(myAllOrder.getTotal());
//
//        System.out.println("测试");
//
//        Page<Task> taskPage = new Page<>();
//        BeanUtils.copyProperties(myAllOrder,taskPage);
//
//        List<Task> records = taskPage.getRecords();
//
//        long size = taskPage.getSize();
//
//        System.out.println(records);
//
//        System.out.println(size);
    }


    @Test
    void test3() throws WxPayException {
//        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
//
//        orderRequest.setBody("轻小南商城商品");
//
//        orderRequest.setTotalFee(BaseWxPayRequest.yuanToFen(String.valueOf(0.01)));//元转成分
//
//        orderRequest.setOpenid("oYSiT65n_Ju4HkNpZNLTJv14ivnM");
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
//        String outTradeNo= RandomUtil.generateRandomNumberString();
//        orderRequest.setOutTradeNo(outTradeNo);
//
//        orderRequest.setNotifyUrl("https://xiaoligongzuoshi.top/wxpay/notify");
//
//        orderRequest.setProductId("88888888");
//
//        orderRequest.setTradeType("JSAPI");//这个是商户的参数
//
//        orderRequest.setProfitSharing("TRUE");
//
//        WxPayMpOrderResult wxPayMpOrderResult=wxPayService.createOrder(orderRequest);
//
//        System.out.println(wxPayMpOrderResult);

    }

    @Resource
    CourseRepository courseRepository;

    @Test
    void test4(){
        Course xiaoli = courseRepository.findCourseByCourseName("xiaoli");



        //System.out.println(callbackAddressProperties.getSuccessAddress());
//
//        CallbackAddressProperties callbackAddressProperties1 = new CallbackAddressProperties();
//        System.out.println(callbackAddressProperties1.getRefundAddress());
        //System.out.println(qiniuProperties.getAccessKey());

//        String s="eyJhbGciOiJIUzI1NiJ9.eyJvcGVuSWQiOiJvWVNpVDY1bl9KdTRIa05wWk5MVEp2MTRpdm5NIiwicmVkaXNUb2tlbiI6IjVhOTJhNTEwNDkwNTRiNDIiLCJleHAiOjE2OTU1ODIxMDJ9.vHKMa4UQ3XziMWB5SrOHOSZP4ErbsGiD3xscjhG5ZNE";
//        boolean tokenExpired = TokenUtils.isTokenExpired(s);
//
//        String s2="eyJhbGciOiJIUzI1NiJ9.eyJvcGVuSWQiOiJvWVNpVDYwYUpueFhta3VsZWp5U1pZc0lRSl9JIiwicmVkaXNUb2tlbiI6ImQyNjIwOTQxMzM3NjQ3MTUiLCJleHAiOjE2OTU1ODI3NDZ9.Bc_MpdnTN-EMZsd9rY6yjSGd_-38Bs-QqMUje6D0aE8";
//        String redisToken = TokenUtils.getRedisToken(s2);
//        String openid = TokenUtils.getOpenid(s2);
//
//        System.out.println("redis:"+redisToken+"openid:"+openid+"booler:"+tokenExpired);

//        Task task = taskMapper.selectById("task:12257bc332c34524b4");
//        System.out.println(task.getStartTime());
//        Course course = new Course();
//        course.setCourseName("小力学java");
//        course.setCourseAttribute("3");
////        mongoTemplate.insert(course);
//        courseRepository.save(course);
//        Task task = taskMapper.selectById("111");
    }
}
