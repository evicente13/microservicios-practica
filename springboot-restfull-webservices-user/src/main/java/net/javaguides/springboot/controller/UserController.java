package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class eUserController
{
	private UserService userService;
	
	
	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
		UserDto savedUser = userService.createUser(user);
	    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}
	
	
	 @GetMapping("/{id}")
	 public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
		 UserDto user = userService.getUserById(userId);
	     return new ResponseEntity<>(user, HttpStatus.OK);
	 }

	 @GetMapping
	 public ResponseEntity<List<UserDto>> getAllUsers(){
		 List<UserDto> users = userService.getAllUsers();
	     return new ResponseEntity<>(users, HttpStatus.OK);
	 }

	 @PutMapping("{id}")
	 public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
	                                           @RequestBody UserDto user){
		 user.setId(userId);
	     UserDto updateUser = userService.updateUser(user);
	     return new ResponseEntity<>(updateUser, HttpStatus.OK);
	 }

	 @DeleteMapping("{id}")
	 public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
		 userService.deleteUser(userId);
	     return new ResponseEntity<>("User successfully deleted!",HttpStatus.OK);
	 }
}
