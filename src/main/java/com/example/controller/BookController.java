package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.UserService;


@RestController
public class BookController {
	@Autowired
	UserService userService;
   /*---Add new book---*/
   
	 @RequestMapping(value={"/book1"},method=RequestMethod.GET)
	   public String get() {
		 System.out.println("__________(++++++++++++++++++==");
		 List<User> users = userService.findAllUsers();
	      return "HELOO";
	   }
}

