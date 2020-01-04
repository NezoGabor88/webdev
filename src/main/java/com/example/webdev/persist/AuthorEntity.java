package com.example.webdev.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@ToString
@Entity
@Table(name = "author")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorEntity {
    @Id
    private Long Id;
    @NotBlank
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "authorEntity")
    @JsonManagedReference
    private List<BookEntity> books;
}
