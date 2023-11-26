package com.example.socialmedia.service;

import com.example.socialmedia.model.User;
import com.example.socialmedia.util.ExcelUtil;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Component
public class UserDaoService{

  private static  List<User> users = new ArrayList<>();
  {
    users.add(new User(1,"Ramya", LocalDate.now().minusYears(31)));
    users.add(new User(2,"Raj", LocalDate.now().minusYears(33)));
    users.add(new User(3,"Hanvi", LocalDate.now().minusYears(6)));
  }
  public List<User> findAllUsers(){
    return users;
  }
  public User saveUser(User user){
    users.add(user);
    return user;
  }
  public User findOneUser(int id){
    Predicate<? super User> predicate = user -> Objects.equals(user.getId(), id);
    return users.stream().filter(predicate).findFirst().get();
  }
  public void deleteUserById(int id){
    Predicate<? super User>predicate=user -> Objects.equals(user.getId(), id);
    users.removeIf(predicate);
  }
  public ByteArrayInputStream getDataDownload() throws IOException {
    List<User> users = findAllUsers();
    return ExcelUtil.dataToExcel(users);
  }
}
