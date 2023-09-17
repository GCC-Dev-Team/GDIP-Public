package com.example.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiniuProperties {
    /**
     * 七牛云
     */
    private String accessKey ;
    /**
     * 七牛云的密钥
     */
    private String secretKey ;
    /**
     * 桶的名字
     */
    private String bucketName ;
    /**
     * 域名
     */
    private String domain ;
}
