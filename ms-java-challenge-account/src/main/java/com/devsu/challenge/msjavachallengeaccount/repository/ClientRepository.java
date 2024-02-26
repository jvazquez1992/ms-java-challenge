package com.devsu.challenge.msjavachallengeaccount.repository;

import com.devsu.challenge.msjavachallengeaccount.configuration.FeignConfig;
import com.devsu.challenge.msjavachallengeaccount.service.dto.GetClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "clientService", url ="${client.ms.base-url}", path = "${client.ms.base-path}", configuration = {FeignConfig.class})
public interface ClientRepository {

    @GetMapping
    ResponseEntity<GetClientResponse> getClient(
            @RequestParam(value = "personIdentification") String personIdentification
    );

}
