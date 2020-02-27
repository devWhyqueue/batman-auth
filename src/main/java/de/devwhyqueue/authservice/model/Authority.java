package de.devwhyqueue.authservice.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Authority {

  @NotNull
  @Size(max = 50)
  @Id
  @Column(length = 50)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Authority)) {
      return false;
    }
    return Objects.equals(name, ((Authority) o).name);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name);
  }

  @Override
  public String toString() {
    return "Authority{" +
        "name='" + name + '\'' +
        "}";
  }
}
