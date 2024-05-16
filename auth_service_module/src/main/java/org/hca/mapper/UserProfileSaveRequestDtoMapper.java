package org.hca.mapper;

import org.hca.dto.request.UserProfileSaveRequestDto;
import org.hca.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProfileSaveRequestDtoMapper {
    UserProfileSaveRequestDtoMapper INSTANCE= Mappers.getMapper(UserProfileSaveRequestDtoMapper.class);
    @Mapping(source = "id", target = "authId")
    UserProfileSaveRequestDto authToUserProfileSaveRequestDto(Auth auth);
}
