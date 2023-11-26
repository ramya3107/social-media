package com.example.socialmedia.controller;

import com.example.socialmedia.model.User;
import com.example.socialmedia.service.UserDaoService;
import jakarta.validation.Valid;
import org.springframework.core.io.InputStreamResource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

  private UserDaoService service;

  public UserController(UserDaoService service){ //constructor injection
    this.service=service;
  }

  @GetMapping("/api/users")
  public List<User> retrieveAllUsers(){
    return service.findAllUsers();
  }

  //http://localhost:8080/users
  //Entity model
  //WebMvcLinkBuilder
  @GetMapping("/users/{id}")
  public EntityModel<User> retrieveOneUser(@PathVariable int id) throws UserNotFoundException {
     User user=service.findOneUser(id);
     if(user==null)
       throw new UserNotFoundException("id:"+id);
       EntityModel<User> entityModel=EntityModel.of(user);
       WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).retrieveAllUsers());
       entityModel.add(link.withRel("all_users"));
       return entityModel;
  }
  @PostMapping("/api/users")
  public void createUser(@Valid @RequestBody User user){
  service.saveUser(user);
  }
  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable int id){
    service.deleteUserById(id);
  }

  @GetMapping("file/download")
  public ResponseEntity<InputStreamResource> downloadFile() throws IOException {
    String fileName = "users.xlsx";
    ByteArrayInputStream dataDownload = service.getDataDownload();
    InputStreamResource inputStreamResource = new InputStreamResource(dataDownload);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+fileName)
        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
        .body(inputStreamResource);
  }
}
