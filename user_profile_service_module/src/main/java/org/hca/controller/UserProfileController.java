package org.hca.controller;

import lombok.RequiredArgsConstructor;
import org.hca.dto.request.UserProfileSaveRequestDto;
import org.hca.dto.request.UserProfileUpdateRequestDto;
import org.hca.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.hca.constant.EndPoints.*;

@RequiredArgsConstructor
@RequestMapping(ROOT+USERPROFILE)
@RestController
public class UserProfileController {
    private final UserProfileService service;

    @PostMapping(SAVE)
    public ResponseEntity<Void> save(@RequestBody UserProfileSaveRequestDto dto){
        //service.saveUserProfile(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getUserIdToken")
    String getUserIdToken(@RequestParam(name = "AuthId") Long authId){
        return service.getUserIdToken(authId);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> update(@RequestBody UserProfileUpdateRequestDto dto){
        return ResponseEntity.ok(service.update(dto));
    }

//    @PostMapping("/submitForm")
//    public String submitForm(@ModelAttribute("dto") UserProfileUpdateRequestDto dto) {
//        service.update(dto);
//        return "success"; // Or redirect to another page
//    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Void> updateStatus(@RequestHeader("Authorization") String token){
        //service.updateStatus(token);
        return ResponseEntity.ok().build();
    }
}
