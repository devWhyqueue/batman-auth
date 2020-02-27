package de.devwhyqueue.authservice.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWT {

  private String token;

  public JWT(String token) {
    this.token = token;
  }
}
