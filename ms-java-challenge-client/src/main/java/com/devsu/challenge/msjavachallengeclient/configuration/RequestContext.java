package com.devsu.challenge.msjavachallengeclient.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.UUID;

@Getter
@Setter
@Component
@RequestScope
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestContext {
    String session = UUID.randomUUID().toString();
}
