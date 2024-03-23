package com.ltnc.be.domain.equipment;

import com.ltnc.be.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "equipments")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Equipment extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "available")
    private boolean available;
}
