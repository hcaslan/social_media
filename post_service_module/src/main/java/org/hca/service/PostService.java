package org.hca.service;

import lombok.RequiredArgsConstructor;
import org.hca.dto.request.PostSaveRequestDto;
import org.hca.dto.request.PostUpdateRequestDto;
import org.hca.entity.EStatus;
import org.hca.entity.Post;
import org.hca.exception.ErrorType;
import org.hca.exception.UserProfileServiceException;
import org.hca.manager.UserManager;
import org.hca.mapper.PostMapper;
import org.hca.repository.PostRepository;
import org.hca.utility.JwtTokenManager;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@EnableMongoAuditing
public class PostService {
    private final PostRepository postRepository;
    private final JwtTokenManager jwtTokenManager;
    private final PostMapper postMapper;
    private final UserManager userManager;
    @Transactional
    public void savePost(String token, PostSaveRequestDto dto) {
        Long authId = jwtTokenManager.getAuthIdFromToken(token).orElseThrow(() -> new UserProfileServiceException(ErrorType.INVALID_TOKEN));

        //Post post = postMapper.dtoToPost(dto);
        Post post = PostMapper.INSTANCE.dtoToPost(dto);

        post.setUserId(jwtTokenManager.getUserIdFromToken(userManager.getUserIdToken(authId)).orElseThrow(() -> new UserProfileServiceException(ErrorType.INVALID_TOKEN)));
        postRepository.save(post);
    }
    @Transactional
    public List<Post> findAll(String token) {
        Long authId = jwtTokenManager.getAuthIdFromToken(token).orElseThrow(() -> new UserProfileServiceException(ErrorType.INVALID_TOKEN));
        jwtTokenManager.getUserIdFromToken(userManager.getUserIdToken(authId)).orElseThrow(() -> new UserProfileServiceException(ErrorType.INVALID_TOKEN));

        return postRepository.findAll();
    }
    @Transactional
    public List<Post> findPostForUser(String token) {
        Long authId = jwtTokenManager.getAuthIdFromToken(token).orElseThrow(() -> new UserProfileServiceException(ErrorType.INVALID_TOKEN));
        String userId = jwtTokenManager.getUserIdFromToken(userManager.getUserIdToken(authId)).orElseThrow(() -> new UserProfileServiceException(ErrorType.INVALID_TOKEN));

        return postRepository.findByUserId(userId);
    }
    @Transactional
    public void deletePost(String token, String postId) {
        Long authId = jwtTokenManager.getAuthIdFromToken(token).orElseThrow(() -> new UserProfileServiceException(ErrorType.INVALID_TOKEN));
        String userId = jwtTokenManager.getUserIdFromToken(userManager.getUserIdToken(authId)).orElseThrow(() -> new UserProfileServiceException(ErrorType.INVALID_TOKEN));
        Post post = postRepository.findById(postId).orElseThrow(() -> new UserProfileServiceException(ErrorType.POST_NOT_FOUND));
        if (post.getUserId().equals(userId)) {
            if(post.getStatus().equals(EStatus.DELETED)){
                throw new UserProfileServiceException(ErrorType.POST_ALREADY_DELETED);
            }
            post.setStatus(EStatus.DELETED);
        }
        postRepository.save(post);
    }
    @Transactional
    public void updatePost(String token, PostUpdateRequestDto dto) {
        Long authId = jwtTokenManager.getAuthIdFromToken(token).orElseThrow(() -> new UserProfileServiceException(ErrorType.INVALID_TOKEN));
        String userId = jwtTokenManager.getUserIdFromToken(userManager.getUserIdToken(authId)).orElseThrow(() -> new UserProfileServiceException(ErrorType.INVALID_TOKEN));
        Post post = postRepository.findById(dto.getId()).orElseThrow(() -> new UserProfileServiceException(ErrorType.POST_NOT_FOUND));
        if (post.getUserId().equals(userId)) {
            if(post.getStatus().equals(EStatus.DELETED)){
                throw new UserProfileServiceException(ErrorType.POST_ALREADY_DELETED);
            }
            if(dto.getPhoto() != null) post.setPhoto(dto.getPhoto());
            if(dto.getTitle() != null) post.setTitle(dto.getTitle());
            if(dto.getContent() != null) post.setContent(dto.getContent());
        }
        postRepository.save(post);
    }
}
