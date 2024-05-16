package org.hca.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BaseEntity {
    @CreationTimestamp
    LocalDateTime createat;
    @UpdateTimestamp
    LocalDateTime updateat;
    @Builder.Default
    Boolean state=true;
}
