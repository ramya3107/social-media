package com.example.socialmedia.controller;

import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaController {

  private UserRepository repository;

  public UserJpaController(UserRepository repository){ //constructor injection
    this.repository=repository;
  }

  @GetMapping("/jpa/api/users")
  public List<User> retrieveAllUsers(){
    return repository.findAll();
  }

  //http://localhost:8080/users
  //Entity model
  //WebMvcLinkBuilder
  @GetMapping("/jpa/users/{id}")
  public EntityModel<User> retrieveOneUser(@PathVariable int id) throws UserNotFoundException {
    Optional<User> user=repository.findById(id);
    if(user.isEmpty())
      throw new UserNotFoundException("id:"+id);
    EntityModel<User> entityModel=EntityModel.of(user.get());
    WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).retrieveAllUsers());
    entityModel.add(link.withRel("all_users"));
    return entityModel;
  }
  @PostMapping("/jpa/api/users")
  public void createUser(@Valid @RequestBody User user){
    repository.save(user);
  }
  @DeleteMapping("/jpa/users/{id}")
  public void deleteUser(@PathVariable int id){
    repository.deleteById(id);
  }

  @GetMapping("/repository/class-name")
  public String repositoryClassName() {
    String s = repository.getClass().toString();
    return s;
  }
}



