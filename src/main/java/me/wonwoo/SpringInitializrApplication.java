package me.wonwoo;

import me.wonwoo.config.SpringClient;
import me.wonwoo.config.SpringProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties(SpringProperties.class)
public class SpringInitializrApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringInitializrApplication.class, args);
  }

  @Bean
  RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  @Bean
  SpringClient springClient(RestTemplate restTemplate, CounterService counterService) {
    return new SpringClient(restTemplate, counterService);
  }
}
