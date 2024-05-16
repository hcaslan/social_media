package org.hca.mapper;

import org.hca.dto.request.RegisterRequestDto;
import org.hca.dto.response.LoginResponseDto;
import org.hca.dto.response.RegisterResponseDto;
import org.hca.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    AuthMapper INSTANCE= Mappers.getMapper(AuthMapper.class);

    Auth toAuth(RegisterRequestDto dto);

    RegisterResponseDto authToRegisterDto(Auth auth);
    LoginResponseDto authToLoginDto(Auth auth);
}