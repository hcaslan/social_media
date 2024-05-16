package org.hca.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(url = "http://localhost:9091/api/v1/userprofile", name = "userManager")
public interface UserManager {
    @GetMapping("/getUserIdToken")
    String getUserIdToken(@RequestParam(name = "AuthId") Long authId);
}