package com.devsu.challenge.msjavachallengeclient.domain;

import com.devsu.challenge.msjavachallengeclient.domain.enums.GenderEnum;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@SuperBuilder
@Entity(name = "person")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.JOINED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public abstract class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long personId;
    String identification;
    String names;
    @Enumerated(EnumType.STRING)
    GenderEnum gender;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date birthDate;
    String address;
    String phone;

}
