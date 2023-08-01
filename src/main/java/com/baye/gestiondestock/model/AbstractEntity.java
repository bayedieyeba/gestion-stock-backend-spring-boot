package com.baye.gestiondestock.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity  implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    //@CreatedDate
    @Column(name = "createDate",nullable = false,updatable = false )
    private Instant creationDate;

    //
    @Column(name = "lastModifiedDate")
    private Instant lastUpdateDate;

   /* @PrePersist
    void prePersist(){
        creationDate = Instant.now();
    }

    @PreUpdate
    void preUpdate(){
        lastUpdateDate = Instant.now();
    }*/
}
