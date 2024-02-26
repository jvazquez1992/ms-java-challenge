package com.devsu.challenge.msjavachallengeaccount.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class ClientMessageError {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
