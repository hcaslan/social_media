package org.hca.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:9090/api/v1/auth", name = "authManager")
public interface AuthManager {
    @PutMapping("/updateEmail")
    ResponseEntity<Void> updateEmail(@RequestHeader("Authorization") String token);
}
