package com.example.webdev.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    @Min(0)
    private Long id;
    @NotBlank
    private String name;
    @Min(0)
    private Integer available;
    @Min(0)
    private Long author_id;
}

