package org.hca.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserProfileUpdateRequestDto {
    private String id;
    private String email;
    private String photo;
    private String phone;
    private String website;
    private String about;
}
