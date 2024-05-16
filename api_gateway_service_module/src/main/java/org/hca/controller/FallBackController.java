package org.hca.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {
    @GetMapping("/auth")
    public ResponseEntity<String> getFallbackAuth(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Auth Service Is Not Responding");
    }
    @GetMapping("/userprofile")
    public ResponseEntity<String> getFallbackUserProfile(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("UserProfile Service Is Not Responding");
    }
    @GetMapping("/post")
    public ResponseEntity<String> getFallbackPost(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Post Service Is Not Responding");
    }
}
