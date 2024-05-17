package org.hca.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostUpdateRequestDto {
    private String id;
    private String title;
    private String content;
    private String photo;
}
