package org.hca.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Document
public class UserProfile {
    @MongoId
    private String id;
    private Long authId;
    private String username;
    private String email;
    private String photo;
    private String phone;
    private String website;
    private String about;
    @Builder.Default
    private EStatus status = EStatus.PENDING;
}
