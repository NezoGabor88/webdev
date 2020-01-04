package com.example.webdev.persist;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name ="book")
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {
    @Id
    private Long id;
    @NotBlank
    private String name;
    @Min(0)
    private Integer available;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private AuthorEntity authorEntity;
}
