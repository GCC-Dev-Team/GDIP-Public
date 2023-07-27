package com.example.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.util.ArrayList;
import java.util.List;

public class QiniuUtil {
    private static final String ACCESS_KEY = "Y29YcvXWUe4k3Su0A63ziWxe86XTHLbcgx0-51Ke";
    private static final String SECRET_KEY = "qjQcw6AbtcsX1LqcAu1E1bUBUMT3L9Yt1S2Ysxo6";
    private static final String BUCKET_NAME = "xiaoli2023";
    private static final String DOMAIN = "yun.xiaoligongzuoshi.top";
    // 上传图片并返回图片名称
    public static String uploadImage( byte[] data,String imageName) throws Exception {
        Configuration cfg = new Configuration(Region.autoRegion());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":\"$(imageInfo.width)\",\"height\":\"$(imageInfo.height)\"}");
        String upToken = auth.uploadToken(BUCKET_NAME, null, 3600, putPolicy);

        Response response = uploadManager.put(data, imageName, upToken);
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        return putRet.key;
    }

    // 根据图片名称获取图片URL
    public static String getImageUrl(String imageName) {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, new Configuration());
        try {
            FileInfo fileInfo = bucketManager.stat(BUCKET_NAME, imageName);
            return "http://" + DOMAIN + "/" + imageName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从七牛云删除图片
     * @param fileNameList 图片名集合
     * @return 返回每张图片的删除结果
     */
    public static Object QiniuCloudDeleteImage(String[] fileNameList){
        //用Json格式传数组时直接传["xxx", "xxx", "xxx", "xxx"]
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());

        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        //创建一个用来接收删除结果的集合
        List<String> list = new ArrayList<>();
        try {
            //单次批量请求的文件数量不得超过1000
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(BUCKET_NAME, fileNameList);
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < fileNameList.length; i++) {
                BatchStatus status = batchStatusList[i];
                if (status.code == 200) {//成功返回文件名+true并添加进集合
                    list.add("{fileName:"+fileNameList[i]+",deleteResult:true}");
                }else {//失败返回文件名+false并添加进集合
                    list.add("{fileName:"+fileNameList[i]+",deleteResult:false}");
                }
            }
        } catch (QiniuException ex) {
            System.err.println("七牛云ERROR:" + ex.response.toString());
        }
        return list;
    }

}
