package com.example.webdev.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;

@AllArgsConstructor
@Data
public class AuthorDTO {
    @Min(0)
    private Long aut_id;
    private String name;
}
