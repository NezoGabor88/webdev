package com.example.webdev.controller;

import com.example.webdev.persist.AuthorEntity;
import com.example.webdev.persist.BookEntity;
import com.example.webdev.persist.BookStoreException;
import com.example.webdev.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @PostMapping("addAuthor")
    @ResponseBody
    public void addAuthor(@Valid @RequestBody AuthorEntity authorEntity, BindingResult bindingResult,HttpServletResponse response){
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
        }
        else {
            boolean success = authorService.AddAuthor(authorEntity);
           if (success){
               response.setStatus(200);
           }
           else
           {
               response.setStatus(409);
           }
        }
    }

    @ExceptionHandler(BookStoreException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public void handleException(BookStoreException e) {
        e.printStackTrace();
    }
    //authorName: Visszatér egy JSON objektummal, ami tartalmazza az Authorok neveit
    @GetMapping("authorName")
    @ResponseBody
    public List<String> authorsName() {
        return authorService.getAuthorsName();
    }
    //???authors: visszatér egy JSON dokumentummal, amiben benne van az összes adatbázisban tárolt Authorok
    @GetMapping("authors")
    @ResponseBody
    public List<AuthorEntity> authorEntities(){
        return authorService.getAuthor();
    }
    //author: visszatér egy JSON objektummal amiben benne van a klienstől cookieban kapott Authorok könyvei
    @GetMapping("author")
    @ResponseBody
    public List<BookEntity> authorCookie(@CookieValue("Cookie_1") String cookieAuthor) {
        return authorService.getAuthorBooks(Long.valueOf(cookieAuthor));
    }

    @PostMapping("addAuthor2")
    @ResponseBody
    public void addAuthors(@Valid @RequestBody AuthorEntity authorEntity, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        else {
            authorService.addAuthorEx(authorEntity);

        }
    }
}
