package org.example.springbootreactiveplayground.comparision01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("traditional")
@Slf4j
public class TraditionalWebController {

    private final RestClient restClient = RestClient.builder()
        .baseUrl("http://localhost:7070/")
        .build();


    @GetMapping("products")
    public List<Product> getProducts() {
        List<Product> list = restClient.get()
            .uri("/demo01/products")
            .retrieve()
            .body(new ParameterizedTypeReference<List<Product>>() {
            });
        log.info("received response {}", list);
        return list;
    }

    @GetMapping("products/notorious")
    public List<Product> getProductsNotorious() {
        List<Product> list = restClient.get()
            .uri("/demo01/products/notorious")
            .retrieve()
            .body(new ParameterizedTypeReference<List<Product>>() {
            });
        log.info("received response {}", list);
        return list;
    }

}
