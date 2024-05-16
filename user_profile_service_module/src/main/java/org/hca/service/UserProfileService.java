package org.hca.service;

import lombok.RequiredArgsConstructor;
import org.hca.dto.request.UserProfileSaveRequestDto;
import org.hca.dto.request.UserProfileUpdateRequestDto;
import org.hca.entity.EStatus;
import org.hca.entity.UserProfile;
import org.hca.exception.ErrorType;
import org.hca.exception.UserProfileServiceException;
import org.hca.manager.AuthManager;
import org.hca.mapper.UserProfileMapper;
import org.hca.repository.UserProfileRepository;
import org.hca.utility.JwtTokenManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;
    private final JwtTokenManager jwtTokenManager;
    private final AuthManager authManager;
    public void saveUserProfile(UserProfileSaveRequestDto dto){
        userProfileRepository.save(UserProfile.builder()
                        .authId(dto.getAuthId())
                        .email(dto.getEmail())
                        .username(dto.getUsername())
                .build());
    }
    @Transactional
    public String update (UserProfileUpdateRequestDto dto){
        UserProfile userProfile = userProfileRepository.findById(dto.getId())
                .orElseThrow(() -> new UserProfileServiceException(ErrorType.USER_NOT_FOUND));
        if(dto.getAbout() != null) userProfile.setAbout(dto.getAbout());
        if(dto.getPhoto() != null) userProfile.setPhoto(dto.getPhoto());
        if(dto.getWebsite() != null) userProfile.setWebsite(dto.getWebsite());
        if(dto.getPhone() != null) userProfile.setPhone(dto.getPhone());
        if (dto.getEmail() != null) {
            authManager.updateEmail(
                    jwtTokenManager.createToken(userProfile.getAuthId(),dto.getEmail())
                            .orElseThrow(()-> new UserProfileServiceException(ErrorType.USER_NOT_FOUND)));
            userProfile.setEmail(dto.getEmail());
        }
        userProfileRepository.save(userProfile);
        return "Update Success!";
    }

    public void updateStatus(String token) {
        Long authId = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new UserProfileServiceException(ErrorType.INVALID_TOKEN));
        EStatus status = jwtTokenManager.getStatusFromToken(token).orElseThrow(() -> new UserProfileServiceException(ErrorType.INVALID_TOKEN));
        UserProfile userProfile = userProfileRepository.findByAuthId(authId).orElseThrow(() -> new UserProfileServiceException(ErrorType.USER_NOT_FOUND));
        userProfile.setStatus(status);
        userProfileRepository.save(userProfile);
    }

    public String getUserIdToken(Long authId) {
        UserProfile userProfile = userProfileRepository.findByAuthId(authId).orElseThrow(() -> new UserProfileServiceException(ErrorType.USER_NOT_FOUND));
        return jwtTokenManager.createTokenForUserId(authId,userProfile.getId()).orElseThrow(() -> new UserProfileServiceException(ErrorType.INVALID_TOKEN));
    }
}
