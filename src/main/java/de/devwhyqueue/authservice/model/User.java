package de.devwhyqueue.authservice.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @Email
  @Size(min = 5, max = 50)
  @Column(unique = true)
  private String email;

  @NotNull
  @Size(min = 5, max = 60)
  @Column(name = "password_hash", length = 60, nullable = false)
  private String password;

  @Size(max = 50)
  @Column(length = 50)
  private String firstName;

  @Size(max = 50)
  @Column(length = 50)
  private String lastName;

  @NotNull
  private Gender gender;

  @Size(min = 5, max = 254)
  private String club;

  @ManyToMany
  @JoinTable(
      name = "user_authority",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
  private Set<Authority> authorities = new HashSet<>();

  public User() {
  }

  public User(Long id, String email, String password, String firstName, String lastName, Gender gender, String club, Set<Authority> authorities) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.club = club;
    this.authorities = authorities;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }
    return id != null && id.equals(((User) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }
}
