package org.hca.mapper;

import org.hca.dto.request.UserProfileSaveRequestDto;
import org.hca.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProfileMapper {
    UserProfileMapper INSTANCE= Mappers.getMapper(UserProfileMapper.class);

    UserProfile dtoToUser(UserProfileSaveRequestDto dto);

}