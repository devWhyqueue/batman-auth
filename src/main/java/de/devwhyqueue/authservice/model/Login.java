package de.devwhyqueue.authservice.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {

  @NotNull
  @Email
  @Size(min = 5, max = 50)
  private String email;

  @NotNull
  @Size(min = 5, max = 50)
  private String password;
}
