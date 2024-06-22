package dev.danielb.demo.dto;

public record Address(
        String street,
        String suite,
        String city,
        String zipcode,
        Geo geo
) {
}
