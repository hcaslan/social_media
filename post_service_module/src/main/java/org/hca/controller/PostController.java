package org.hca.controller;

import lombok.RequiredArgsConstructor;
import org.hca.dto.request.PostSaveRequestDto;
import org.hca.dto.request.PostUpdateRequestDto;
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
    public ResponseEntity<List<Post>> findAll(@RequestParam(name = "token") String token){
        return ResponseEntity.ok(service.findAll(token));
    }
    @GetMapping("/findByUserId")
    public ResponseEntity<List<Post>> findPostForUser(@RequestParam(name = "token") String token){
        return ResponseEntity.ok(service.findPostForUser(token));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePost(@RequestParam(name = "token") String token, @RequestParam(name = "postId") String postId){
        service.deletePost(token,postId);
        return ResponseEntity.ok("Success");
    }
    @PutMapping("/update")
    public ResponseEntity<String> updatePost(@RequestParam(name = "token") String token, @RequestBody PostUpdateRequestDto dto){
        service.updatePost(token,dto);
        return ResponseEntity.ok("Success");
    }
}
