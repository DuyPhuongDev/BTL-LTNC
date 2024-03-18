package com.ltnc.be.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Long id;

  @Column(name = "is_active", nullable = false, columnDefinition = "BOOLEAN")
  protected boolean isActive = Boolean.TRUE;

  @Column(name = "created_at", nullable = false)
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