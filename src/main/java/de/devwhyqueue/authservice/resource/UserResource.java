package de.devwhyqueue.authservice.resource;

import de.devwhyqueue.authservice.model.User;
import de.devwhyqueue.authservice.repository.UserRepository;
import de.devwhyqueue.authservice.security.AuthoritiesConstants;
import de.devwhyqueue.authservice.service.UserService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserResource {

  private final Logger log = LoggerFactory.getLogger(UserResource.class);

  private final UserService userService;
  private final UserRepository userRepository;

  public UserResource(UserService userService,
      UserRepository userRepository) {
    this.userService = userService;
    this.userRepository = userRepository;
  }

  @GetMapping("/users/self")
  public ResponseEntity<User> getUser() {
    log.debug("REST request to get own user");
    return userService.getUserWithAuthorities()
        .map(user -> {
          user.setPassword("[PROTECTED]");
          return ResponseEntity.ok(user);
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/users")
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
    log.debug("REST request to save User : {}", user);

    if (user.getId() != null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      // Lowercase the user login before comparing with database
    } else if (userRepository.findOneByEmailIgnoreCase(user.getEmail()).isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already in use!");
    } else {
      User newUser = userService.registerUser(user);
      return ResponseEntity.created(new URI("/api/users/" + newUser.getEmail())).body(newUser);
    }
  }

  @PutMapping("/users")
  @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
  public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
    log.debug("REST request to update User : {}", user);

    Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(user.getEmail());
    if (existingUser.isPresent() && (!existingUser.get().getId().equals(user.getId()))) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already in use!");
    }
    Optional<User> updatedUser = userService
        .updateOwnUser(user.getEmail(), user.getFirstName(), user.getLastName(), user.getGender(),
            user.getClub());

    return updatedUser.map(u -> {
      u.setPassword("[PROTECTED]");
      return ResponseEntity.ok(u);
    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/users/self")
  @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
  public ResponseEntity<Void> deleteUser() {
    log.debug("REST request to delete own user");
    userService.deleteUser();
    return ResponseEntity.noContent().build();
  }
}
