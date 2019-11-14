/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */

package com.stingion.makeitfine.controller;

import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.service.UserService;
import com.stingion.makeitfine.util.UserPasswordEncoder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("user")
@Api(tags = {"UserController"})
public class UserController {

  private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @Autowired
  private UserPasswordEncoder passwordEncoder;

  @GetMapping("userManagement")
  public ModelAndView getIndexPage() {
    return new ModelAndView("userManagement");
  }

  @GetMapping
  @ApiOperation("Get all users")
  public List<User> listAllUsers() {
    return userService.findAll();
  }

  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public User getUser(@PathVariable("id") int id) {
    return userService.findById(id);
  }

  @PostMapping
  public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
    if (userService.findBySSO(user.getSsoId()) != null) {
      LOG.debug("User with ssoID {} exists", user.getSsoId());
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    passwordEncoder.encodePassword(user);

    userService.save(user);
    LOG.info("User created {}", user);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @PutMapping(value = "{id}")
  public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
    User currentUser = userService.findById(id);
    if (currentUser == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    passwordEncoder.encodePassword(user);

    currentUser.setSsoId(user.getSsoId());
    currentUser.setPassword(user.getPassword());
    currentUser.setEmail(user.getEmail());
    currentUser.setState(user.getState());

    userService.update(currentUser);
    LOG.info("Updating User {}", currentUser);
    return new ResponseEntity<>(currentUser, HttpStatus.OK);
  }

  @DeleteMapping(value = "{id}")
  public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
    User user = userService.findById((int) id);
    if (user == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    userService.delete(user);
    LOG.info("Deleted User {}", user);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
