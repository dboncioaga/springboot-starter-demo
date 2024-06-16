package com.danielb.demo.restclient.dto;

public record Address(
        String street,
        String suite,
        String city,
        String zipcode,
        Geo geo
) {
}
