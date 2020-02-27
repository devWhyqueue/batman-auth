package de.devwhyqueue.authservice.service;

import de.devwhyqueue.authservice.model.Authority;
import de.devwhyqueue.authservice.model.Gender;
import de.devwhyqueue.authservice.model.User;
import de.devwhyqueue.authservice.repository.AuthorityRepository;
import de.devwhyqueue.authservice.repository.UserRepository;
import de.devwhyqueue.authservice.security.AuthoritiesConstants;
import de.devwhyqueue.authservice.security.SecurityUtils;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

  private final Logger log = LoggerFactory.getLogger(UserService.class);


  private final UserRepository userRepository;
  private final AuthorityRepository authorityRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository,
      AuthorityRepository authorityRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.authorityRepository = authorityRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional(readOnly = true)
  public Optional<User> getUserWithAuthorities() {
    return SecurityUtils.getCurrentUserEmail().flatMap(userRepository::findOneWithAuthoritiesByEmailIgnoreCase);
  }


  public User registerUser(User user) {
    String encryptedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encryptedPassword);

    Set<Authority> authorities = new HashSet<>();
    authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
    user.setAuthorities(authorities);
    userRepository.save(user);
    log.debug("Created Information for User: {}", user);

    return new User(user.getId(), user.getEmail(), "[PROTECTED]", user.getFirstName(), user.getLastName(), user.getGender(), user.getClub(), user.getAuthorities());
  }

  public Optional<User> updateOwnUser(String email, String firstName, String lastName, Gender gender, String club) {
    return Optional.of(SecurityUtils.getCurrentUserEmail()
        .flatMap(userRepository::findOneByEmailIgnoreCase))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(user -> {
          user.setEmail(email.toLowerCase());
          user.setFirstName(firstName);
          user.setLastName(lastName);
          user.setGender(gender);
          user.setClub(club);
          log.debug("Changed Information for User: {}", user);
          return user;
        });
  }

  public void deleteUser() {
    SecurityUtils.getCurrentUserEmail()
        .ifPresent(email -> {
          userRepository.deleteByEmailIgnoreCase(email);
          log.debug("Deleted User with email: {}", email);
        });
  }
}
