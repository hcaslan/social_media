package org.hca.service;

import lombok.RequiredArgsConstructor;
import org.hca.dto.request.PostSaveRequestDto;
import org.hca.entity.Post;
import org.hca.exception.ErrorType;
import org.hca.exception.UserProfileServiceException;
import org.hca.manager.UserManager;
import org.hca.mapper.PostMapper;
import org.hca.repository.PostRepository;
import org.hca.utility.JwtTokenManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final JwtTokenManager jwtTokenManager;
    private final PostMapper postMapper;
    private final UserManager userManager;
    public void savePost(String token, PostSaveRequestDto dto) {
        Long authId = jwtTokenManager.getAuthIdFromToken(token).orElseThrow(() -> new UserProfileServiceException(ErrorType.INVALID_TOKEN));
        Post post = postMapper.dtoToPost(dto);
        post.setUserId(jwtTokenManager.getUserIdFromToken(userManager.getUserIdToken(authId)).orElseThrow(() -> new UserProfileServiceException(ErrorType.INVALID_TOKEN)));
        postRepository.save(post);
    }
}
