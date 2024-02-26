package com.devsu.challenge.msjavachallengeaccount.domain;

import com.devsu.challenge.msjavachallengeaccount.domain.enums.MovementTypeEnum;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
@Entity(name="movement")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long movementId;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    AccountEntity account;
    String movementDescription;
    BigDecimal mainBalance;
    BigDecimal amount;
    BigDecimal finalBalance;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date date;
    @Enumerated(EnumType.STRING)
    MovementTypeEnum movementType;

}
