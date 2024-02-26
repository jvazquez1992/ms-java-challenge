package com.devsu.challenge.msjavachallengeclient.domain;

import com.devsu.challenge.msjavachallengeclient.domain.enums.ClientStatusEnum;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@Entity(name = "client")
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ClientEntity extends PersonEntity {
    String password;
    @Enumerated(EnumType.STRING)
    ClientStatusEnum status;

}
