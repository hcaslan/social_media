package org.hca.controller;

import lombok.RequiredArgsConstructor;
import org.hca.dto.request.PostSaveRequestDto;
import org.hca.entity.Post;
import org.hca.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hca.constant.EndPoints.*;

@RestController
@RequestMapping(ROOT+ POST)
@RequiredArgsConstructor
public class PostController {
    private final PostService service;
    @PostMapping("/createpost")
    public ResponseEntity<String> createPost(@RequestParam(name = "token") String token, @RequestBody PostSaveRequestDto dto){
        service.savePost(token,dto);
        return ResponseEntity.ok("Success");
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Post>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/findByUserId")
    public ResponseEntity<List<Post>> findPostForUser(@RequestParam(name = "UserId") String userId){
        return ResponseEntity.ok(service.findPostForUser(userId));
    }
}
