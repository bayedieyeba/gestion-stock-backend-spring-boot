package com.baye.gestiondestock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity  implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(name = "createDate" , nullable = false)
    @JsonIgnore // pour dire qu'on en n'a pas besoin lorsqu'on invoque l'api
    private Instant creationDate;

    @LastModifiedDate
    @Column(name = "lastModifiedDate" , nullable = false)
    @JsonIgnore
    private Instant lastUpdateDate;
}
