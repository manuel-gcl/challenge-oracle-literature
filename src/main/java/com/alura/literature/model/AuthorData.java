package com.alura.literature.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorData(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") int birth_year,
        @JsonAlias("death_year") int death_year
) {
}