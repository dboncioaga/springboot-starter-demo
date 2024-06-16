package com.danielb.demo.restclient.client;

import com.danielb.demo.restclient.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.List;

public class UserClient {

    private static final Logger log = LoggerFactory.getLogger(UserClient.class);
    private final RestClient restClient;


    public UserClient(@Qualifier("externalRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public List<User> findAll() {
        log.info("Fetching all users");
        return restClient.get()
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public User findById(String id) {
        log.info("Fetching user with id: {}", id);
        return restClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .body(User.class);
    }

    public User create(User user) {
        log.info("Creating user: {}", user);
        return restClient.post()
                .uri("/users")
                .body(user)
                .retrieve()
                .body(User.class);
    }

    public User update(User user) {
        log.info("Updating user: {}", user);
        return restClient.put()
                .uri("/users/{id}", user.id())
                .body(user)
                .retrieve()
                .body(User.class);
    }

    public ResponseEntity<Void> delete(String id) {
        log.info("Deleting user with id: {}", id);
        return restClient.delete()
                .uri("/users/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

}
