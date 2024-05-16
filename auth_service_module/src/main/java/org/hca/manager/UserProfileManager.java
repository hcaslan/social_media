package org.hca.manager;

import org.hca.dto.request.UserProfileSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.hca.constant.EndPoints.SAVE;

@FeignClient(url = "http://localhost:9091/api/v1/userprofile", name = "userProfileManager")
public interface UserProfileManager {
    @PostMapping(SAVE)
    ResponseEntity<Void> save(@RequestBody UserProfileSaveRequestDto dto);
    @PutMapping("/updateStatus")
    ResponseEntity<Void> updateStatus(@RequestHeader("Authorization") String token);
}
