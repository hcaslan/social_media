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
public class Post {
    @MongoId
    private String id;
    private String userId;
    private String title;
    private String content;
    private String photo;
    @Builder.Default
    private EStatus status = EStatus.PENDING;
}
