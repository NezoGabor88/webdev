package com.example.webdev.controller;

import com.example.webdev.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {
    private BookService bookService;

    @Autowired

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
//addBook: Book hozzáadása az adatbázishoz. Ha van már ilyen nevű Book akkor adjon 409-es hibát ad.
    @PostMapping("addBook")
    @ResponseBody
    public void addBook(@Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
        }
        else {
            bookService.addBook(bookDTO);
        }
    }
    // /books: visszatér egy JSON dokumentummal, amiben benne van az összes adatbázisban tárolt Book
    @GetMapping("getAllbook")
    @ResponseBody
    public List<BookDTO> getAllBook(){
        return bookService.getAllBook();
    }
    @PostMapping("updateBook")
    @ResponseBody
    public void update(@Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        else {
            bookService.update(bookDTO);
        }
    }

 }
