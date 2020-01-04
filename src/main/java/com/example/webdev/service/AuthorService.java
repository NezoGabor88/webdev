package com.example.webdev.service;

import com.example.webdev.persist.AuthorEntity;
import com.example.webdev.persist.AuthorRepository;
import com.example.webdev.persist.BookEntity;
import com.example.webdev.persist.BookStoreException;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public boolean AddAuthor(AuthorEntity authorEntity){
        for (AuthorEntity authorEntity1 : authorRepository.findAll()){
            if (authorEntity.getName().equals(authorEntity1.getName()))
                return false;
        }
        authorRepository.save(authorEntity);
        return true;
    }
    public List<AuthorEntity>getAuthor(){
        return (List<AuthorEntity>)authorRepository.findAll();


    }
    public AuthorEntity getAuthor(Long id){
        return authorRepository.findById(id).get();
    }
    public List<BookEntity> getAuthorBooks(Long authorId) {
        return authorRepository.findById(authorId).get().getBooks();
    }
    public List<String> getAuthorsName() {
        /*List<String> authorsName = new ArrayList<>();
        for (AuthorEntity author : (List<AuthorEntity>) authorRepository.findAll())
        {
            authorsName.add(author.getName());
        }
        return authorsName;*/
        return authorRepository.selectAuthorsName();
    }

    public void addAuthorEx(AuthorEntity authorEntity)  {
        for (AuthorEntity authorEntity1 : authorRepository.findAll()){
            if(authorEntity.getName().equals(authorEntity1.getName()))
                throw new BookStoreException("Az író már létezik");
        }
        authorRepository.save(authorEntity);
    }
}

