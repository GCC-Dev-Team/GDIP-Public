package com.example.utils;

import com.example.config.QiniuProperties;
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
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Component
public class QiniuUtil {

    @Resource
    QiniuProperties qiniuProperties;

    // 上传图片并返回图片名称
    public void uploadImage(byte[] data, String imageName) throws Exception {
        Configuration cfg = new Configuration(Region.autoRegion());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":\"$(imageInfo.width)\",\"height\":\"$(imageInfo.height)\"}");
        String upToken = auth.uploadToken(qiniuProperties.getBucketName(), null, 3600, putPolicy);

        Response response = uploadManager.put(data, imageName, upToken);
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
    }

    // 根据图片名称获取图片URL
    public String getImageUrl(String imageName) {
        Auth auth = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, new Configuration());
        try {
            FileInfo fileInfo = bucketManager.stat(qiniuProperties.getBucketName(), imageName);
            return "http://" + qiniuProperties.getDomain() + "/" + imageName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从七牛云删除图片
     *
     * @param fileNameList 图片名集合
     */
    public  void quinineCloudDeleteImage(String[] fileNameList){
        //用Json格式传数组时直接传["xxx", "xxx", "xxx", "xxx"]
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());

        Auth auth = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        //创建一个用来接收删除结果的集合
        List<String> list = new ArrayList<>();
        try {
            //单次批量请求的文件数量不得超过1000
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(qiniuProperties.getBucketName(), fileNameList);
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
    }

}
