package com.example.webdev.persist;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

    @Query(value ="select name from author", nativeQuery = true)
    public List<String>selectAuthorsName();
}
