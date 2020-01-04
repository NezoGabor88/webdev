package com.example.webdev.service;

import com.example.webdev.controller.BookDTO;
import com.example.webdev.persist.BookEntity;
import com.example.webdev.persist.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;
    private AuthorService authorService;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public void addBook(BookDTO bookDTO){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookDTO.getId());
        bookEntity.setName(bookDTO.getName());
        bookEntity.setAvailable(bookDTO.getAvailable());
        bookEntity.setAuthorEntity(authorService.getAuthor(bookDTO.getAuthor_id()));
        bookRepository.save(bookEntity);
    }
    public List<BookDTO>getAllBook(){
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (BookEntity bookEntity : bookRepository.findAll()){
            bookDTOList.add(
                    new BookDTO(
                            bookEntity.getId(),
                            bookEntity.getName(),
                            bookEntity.getAvailable(),
                            bookEntity.getAuthorEntity().getId()));
        }
        return bookDTOList;
    }
    public void update(BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookDTO.getId());
        bookEntity.setName(bookDTO.getName());
        bookEntity.setAvailable(bookDTO.getAvailable());
        bookEntity.setAuthorEntity(authorService.getAuthor(bookDTO.getAuthor_id()));
        bookRepository.save(bookEntity);
    }


    public void delBook(@RequestParam Long delBook) {
        bookRepository.deleteById(delBook);
    }
}
