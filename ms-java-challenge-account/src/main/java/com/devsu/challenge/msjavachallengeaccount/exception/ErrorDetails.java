package com.devsu.challenge.msjavachallengeaccount.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ErrorDetails {
    String message;
    String details;
}
