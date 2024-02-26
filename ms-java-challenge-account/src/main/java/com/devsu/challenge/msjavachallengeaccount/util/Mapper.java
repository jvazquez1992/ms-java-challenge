package com.devsu.challenge.msjavachallengeaccount.util;

import com.devsu.challenge.msjavachallengeaccount.domain.AccountEntity;
import com.devsu.challenge.msjavachallengeaccount.domain.MovementEntity;
import com.devsu.challenge.msjavachallengeaccount.models.*;
import com.devsu.challenge.msjavachallengeaccount.service.dto.GetClientResponse;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface Mapper {

    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

    @Mapping(source = "getClientResponse.names", target = "personName")
    @Mapping(source = "getClientResponse.identification", target = "personIdentification")
    GetAccountResponse toGetAccountResponse(AccountEntity account, GetClientResponse getClientResponse);

    AccountEntity toAccountEntity(PostAccountRequest postAccountRequest, GetClientResponse getClientResponse);

    PostAccountResponse toPostAccountResponse(AccountEntity accountEntity);

    PutAccountResponse toPutAccountResponse(AccountEntity accountEntity);

    @Mapping(target = "accountNumber", source = "movementEntity.account.accountNumber")
    @Mapping(target = "accountType", source = "movementEntity.account.accountType")
    GetMovementResponse toGetMovementResponse(MovementEntity movementEntity);

    @Mapping(source = "account", target = "account")
    @Mapping(source = "account.balance", target = "mainBalance")
    MovementEntity toMovementEntity(PostMovementRequest postMovementRequest, AccountEntity account);

    @Mapping(source = "movementEntity.account.accountNumber", target = "accountNumber")
    @Mapping(source = "movementEntity.account.balance", target = "balance")
    PostMovementResponse toPostMovementResponse(MovementEntity movementEntity);

    @Mapping(source = "movementEntity.account.accountNumber", target = "accountNumber")
    @Mapping(source = "movementEntity.account.balance", target = "balance")
    PutMovementResponse toPutMovementResponse(MovementEntity movementEntity);


    @Mapping(source = "getClientResponse.identification", target = "personIdentification")
    @Mapping(source = "getClientResponse.names", target = "names")
    @Mapping(source = "account.accountNumber", target = "accountNumber")
    @Mapping(source = "account.balance", target = "balance")
    @Mapping(target = "details", ignore = true)
    GetReportResponse toGetReportResponse(GetClientResponse getClientResponse, AccountEntity account);

    @Mapping(source = "finalBalance", target = "balance")
    GetReportDetailsResponse toGetReportDetailsResponse(MovementEntity movementEntity);

}
