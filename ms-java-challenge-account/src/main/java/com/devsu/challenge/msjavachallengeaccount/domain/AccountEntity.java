package com.devsu.challenge.msjavachallengeaccount.domain;

import com.devsu.challenge.msjavachallengeaccount.domain.enums.AccountStatusEnum;
import com.devsu.challenge.msjavachallengeaccount.domain.enums.AccountTypeEnum;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@Entity(name="account")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;
    String accountNumber;
    @Enumerated(EnumType.STRING)
    AccountTypeEnum accountType;
    BigDecimal balance;
    Long personId;
    @Enumerated(EnumType.STRING)
    AccountStatusEnum status;

}
