package com.payment.adyen.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

@Configuration
@ComponentScan("com.payment.adyen")
public class AppConfig {

    private static final String MONGO_CONNECTION_URL_PARAM = "MONGO_CONNECTION_URL_PARAM";
    private static final String DEFAULT_MONGO_CONNECTION_URL = "mongodb://localhost:27017";
    private static final String PAYMENT_DB = "payment";
    private static final String PAYMENTS_COLLECTION = "payments";

    private final Set<Converter<?, ?>> converters;
    private final PaymentRestTemplateErrorHandler paymentRestTemplateErrorHandler;

    public AppConfig(PaymentRestTemplateErrorHandler paymentRestTemplateErrorHandler, Set<Converter<?, ?>> converters) throws ClassNotFoundException {
        Class.forName("com.payment.adyen.config.AppConfiguration");
        this.paymentRestTemplateErrorHandler = paymentRestTemplateErrorHandler;
        this.converters = converters;
    }

    @Bean
    RestTemplate restTemplate() {
        class RestTemplateHeaderModifierInterceptor
                implements ClientHttpRequestInterceptor {

            @NotNull
            @Override
            public ClientHttpResponse intercept(
                    @NotNull HttpRequest request,
                    @NotNull byte[] body,
                    ClientHttpRequestExecution execution) throws IOException {
                System.out.println(new String(body));
                return execution.execute(request, body);
            }
        }

        var restTemplate = new RestTemplate();

        var interceptors
                = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new RestTemplateHeaderModifierInterceptor());

        restTemplate.setInterceptors(interceptors);
        restTemplate.setErrorHandler(paymentRestTemplateErrorHandler);
        return restTemplate;
    }

    @Bean
    CorsFilter corsFilter() {
        var source = new UrlBasedCorsConfigurationSource();
        var config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    MongoClient mongoClient() {
        var mongoUrl = Objects.requireNonNullElse(System.getenv(MONGO_CONNECTION_URL_PARAM), DEFAULT_MONGO_CONNECTION_URL);
        return MongoClients.create(mongoUrl);
    }

    @Bean
    MongoCollection<Document> paymentsCollection() {
        return mongoClient().getDatabase(PAYMENT_DB).getCollection(PAYMENTS_COLLECTION);
    }

    @Bean
    @Primary
    ConversionService conversionService() {
        var bean = new ConversionServiceFactoryBean();
        bean.setConverters(converters);
        bean.afterPropertiesSet();
        return bean.getObject();
    }

}
