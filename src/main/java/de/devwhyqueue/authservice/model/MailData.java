package de.devwhyqueue.authservice.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailData {

  @NotNull
  @Email
  @Size(min = 5, max = 50)
  private String email;
  @NotNull
  @Size(min = 1, max = 50)
  private String firstName;
  @NotNull
  @Size(min = 5, max = 50)
  private String serverUrl;
  @NotNull
  @Size(max = 20)
  private String key;

  public MailData() {
  }
}
