package com.my.poc.eurekaclienttwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping
public class EurekaClientTwoHelloController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${eureka-client-one-logical-name}")
    private String eurekaClientOneLogicalName;

    @GetMapping("hello")
    public ResponseEntity<String> sayHelloEurekaClientTwo() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://" + eurekaClientOneLogicalName)
                .pathSegment("hello").build().toUri();

        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        String hello = restTemplate.exchange(requestEntity, String.class).getBody();
        return ResponseEntity.ok("You have called Eureka Client Two, fetching response from Eureka Client One: " + hello);
    }

}
