package com.danielb.demo.restclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("external-service")
public record ExternalServiceProperties(
        @DefaultValue("https://jsonplaceholder.typicode.com")
        String baseUrl
) {
}
