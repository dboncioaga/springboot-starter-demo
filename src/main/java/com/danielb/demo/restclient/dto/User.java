package com.danielb.demo.restclient.dto;

public record User(
        String id,
        String name,
        String username,
        String email,
        Address address,
        String phone,
        String website,
        Company company
) {
}
