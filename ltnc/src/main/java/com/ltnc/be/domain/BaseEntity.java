package com.ltnc.be.domain;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Primary;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "created_at", nullable = false) // date epoch
  private Long createdAt;

  @Column(name = "updated_at", nullable = false)
  private Long updatedAt;

  @PrePersist
  protected void prePersist() {
    if (this.createdAt == null) createdAt = Instant.now().toEpochMilli();
    if (this.updatedAt == null) updatedAt = Instant.now().toEpochMilli();
  }

  @PreUpdate
  protected void preUpdate() {
    this.updatedAt = Instant.now().toEpochMilli();
  }

  @PreRemove
  protected void preRemove() {
    this.updatedAt = Instant.now().toEpochMilli();
  }
}
