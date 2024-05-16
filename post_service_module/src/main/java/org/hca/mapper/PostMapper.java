package org.hca.mapper;


import org.hca.dto.request.PostSaveRequestDto;
import org.hca.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    PostMapper INSTANCE= Mappers.getMapper(PostMapper.class);

    Post dtoToPost(PostSaveRequestDto dto);

}