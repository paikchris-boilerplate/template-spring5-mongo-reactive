package com.springreactive.demo;

import com.springreactive.demo.model.Event;
import com.springreactive.demo.model.reactiverepository.EventReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    @Autowired
    EventReactiveRepository eventReactiveRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        bootstrapDatabase();

        WebClient client = WebClient.create("http://localhost:8080");

        WebClient client3 = WebClient
                .builder()
                .baseUrl("http://localhost:8080")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();

        client3.get().uri("/hello")
                .accept(MediaType.TEXT_PLAIN)
                .exchange();
    }
    private void bootstrapDatabase() {
        clearDatabase();
        createEvents();
    }
    private void clearDatabase(){
        eventReactiveRepository.deleteAll();
    }
    private void createEvents(){
        System.out.println("CREATING EVENTS...");
        int count = 0;
        for (int j = 0; j < 10; j++) {
            count++;
            eventReactiveRepository.save(new Event("Name"));
        }

        System.out.println(count + " EVENTS created");
    }

}
