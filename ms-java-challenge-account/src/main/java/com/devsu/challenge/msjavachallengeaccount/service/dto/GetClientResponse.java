package com.devsu.challenge.msjavachallengeaccount.service.dto;

import lombok.Data;

@Data
public class GetClientResponse {
    private Long personId;
    private String identification;
    private String names;
    private String address;
    private String phone;
}
