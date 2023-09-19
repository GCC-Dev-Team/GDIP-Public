package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix ="verify-address")
public class StudentVerifyProperties {

    private String verifyAddress;

    private String verifyAddressNew;
}
