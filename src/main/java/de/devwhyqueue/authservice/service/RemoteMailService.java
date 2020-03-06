package de.devwhyqueue.authservice.service;

import de.devwhyqueue.authservice.model.MailData;
import de.devwhyqueue.authservice.service.exception.MailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteMailService {

  private final Logger log = LoggerFactory.getLogger(RemoteMailService.class);

  @Value("${mail.url}")
  private String mailServer;
  private RestTemplate restTemplate;

  public RemoteMailService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public ResponseEntity<Void> sendActivationMail(MailData mailData) throws MailException {
    try {
      ResponseEntity<Void> response = restTemplate.exchange(
          mailServer + "users/activate", HttpMethod.POST, new HttpEntity<>(mailData),
          new ParameterizedTypeReference<>() {
          });
      return response;
    } catch (RestClientException ex) {
      log.error("Could not send activation mail via {}", mailServer);
      throw new MailException();
    }
  }
}
