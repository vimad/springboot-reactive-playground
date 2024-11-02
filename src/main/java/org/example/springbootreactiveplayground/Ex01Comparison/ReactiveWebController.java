package org.example.springbootreactiveplayground.Ex01Comparison;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("reactive")
@Slf4j
public class ReactiveWebController {

    private final WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:7070/")
        .build();


    @GetMapping("products")
    public Flux<Product> getProducts() {
        return webClient.get()
            .uri("/demo01/products")
            .retrieve()
            .bodyToFlux(Product.class)
            .doOnNext(product -> log.info("received response {}", product));
    }

    @GetMapping("products/notorious")
    public Flux<Product> getProductsNotorious() {
        return webClient.get()
            .uri("/demo01/products/notorious")
            .retrieve()
            .bodyToFlux(Product.class)
            .onErrorComplete()
            .doOnNext(product -> log.info("received response {}", product));
    }

    @GetMapping(value = "products/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> getProductStream() {
        return webClient.get()
            .uri("/demo01/products")
            .retrieve()
            .bodyToFlux(Product.class)
            .doOnNext(product -> log.info("received response {}", product));
    }

}
