package de.devwhyqueue.authservice.service.exception;

public class EmailAlreadyUsedException extends Exception {

  public EmailAlreadyUsedException() {
    super("Email already used!");
  }
}
