package com.danielb.demo.restclient;

import com.danielb.demo.restclient.client.UserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@AutoConfiguration
@EnableConfigurationProperties(ExternalServiceProperties.class)
public class ExternalServiceConfiguration {

    private final ExternalServiceProperties properties;
    Logger log = LoggerFactory.getLogger(ExternalServiceConfiguration.class);

    public ExternalServiceConfiguration(ExternalServiceProperties properties) {
        this.properties = properties;
        log.info("ExternalServiceConfiguration created with properties: {}", properties);
    }

    @Bean
    UserClient userClient(RestClient restClient) {
        return new UserClient(restClient);
    }

    @Bean("externalRestClient")
    RestClient restClient(RestClient.Builder builder) {
        return builder.baseUrl(properties.baseUrl()).build();
    }


}
