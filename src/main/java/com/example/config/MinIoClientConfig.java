package com.example.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class MinIoClientConfig {
	@Value("${oss.endpoint}")
    private String url;
    @Value("${oss.access-key}")
    private String accessKey;
    @Value("${oss.secret-key}")
    private String secretKey;
    @Bean
    public MinioClient minioClient(){
 
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }
 }
