package com.devsu.challenge.msjavachallengeclient.util;

import com.devsu.challenge.msjavachallengeclient.domain.ClientEntity;
import com.devsu.challenge.msjavachallengeclient.models.GetClientResponse;
import com.devsu.challenge.msjavachallengeclient.models.PostClientRequest;
import com.devsu.challenge.msjavachallengeclient.models.PostClientResponse;
import com.devsu.challenge.msjavachallengeclient.models.PutClientResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface Mapper {
    Mapper INSTANCE = Mappers.getMapper(Mapper.class);
    GetClientResponse toGetClientResponse(ClientEntity clientEntity);
    PostClientResponse toPostClientResponse(ClientEntity clientEntity);
    ClientEntity toClientEntityFromRequest(PostClientRequest postClientRequest, String status);
    PutClientResponse toPutClientResponse(ClientEntity clientEntity);

}
