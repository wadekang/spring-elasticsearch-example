package com.example.elasticsearch.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ElasticsearchConfig {

    @Value("${spring.elasticsearch.url}")
    private String hostname;

    @Value("${spring.elasticsearch.port}")
    private Integer port;

    @Value("${spring.elasticsearch.socket-timeout}")
    private Integer socketTimeout;

    @Value("${spring.elasticsearch.connect-timeout}")
    private Integer connectTimeout;

//    @Value("${spring.elasticsearch.maxRetry-timeout}")
//    private Integer maxRetryTimeoutMillis;

    @Bean
    public RestClient restClient() {
        log.debug("socketTimeout         : {}", socketTimeout);
        log.debug("connectTimeout        : {}", connectTimeout);
//        log.debug("maxRetryTimeoutMillis : {}", maxRetryTimeoutMillis);

        RestClient restClient = RestClient.builder(new HttpHost(hostname, port)).setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {

            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                return requestConfigBuilder.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout);
            }
        }).build();

        return restClient;
    }
}
