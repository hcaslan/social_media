package org.hca.service;

import lombok.RequiredArgsConstructor;
import org.hca.dto.request.ActivateCodeRequestDto;
import org.hca.dto.request.LoginRequestDto;
import org.hca.dto.request.RegisterRequestDto;
import org.hca.dto.request.UserProfileSaveRequestDto;
import org.hca.entity.Auth;
import org.hca.entity.ERole;
import org.hca.entity.EStatus;
import org.hca.exception.AuthMicroServiceException;
import org.hca.exception.ErrorType;
import org.hca.manager.UserProfileManager;
import org.hca.mapper.AuthMapper;
import org.hca.mapper.UserProfileSaveRequestDtoMapper;
import org.hca.repository.AuthRepository;
import org.hca.utility.CodeGenerator;
import org.hca.utility.JwtTokenManager;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final AuthMapper authMapper;
    private final JwtTokenManager jwtTokenManager;
    private final UserProfileManager userProfileManager;
    private final UserProfileSaveRequestDtoMapper userProfileMapper;
    private final RabbitTemplate rabbitTemplate;


    @Transactional
    public Auth register(RegisterRequestDto dto) {
        if (!dto.getPassword().equals(dto.getRePassword())) throw new AuthMicroServiceException(ErrorType.PASSWORDS_NOT_MATCHED);
        if (authRepository.existsByUsername(dto.getUsername())) throw new AuthMicroServiceException(ErrorType.USERNAME_ALREADY_TAKEN);

        Auth auth = AuthMapper.INSTANCE.toAuth(dto);
        auth.setActivationCode(CodeGenerator.getActivationCode());
        System.out.println(auth.getActivationCode());

        authRepository.save(auth);

        UserProfileSaveRequestDto saveRequest = UserProfileSaveRequestDtoMapper.INSTANCE.authToUserProfileSaveRequestDto(auth);

        //userProfileManager.save(saveRequest);
        rabbitTemplate.convertAndSend("exchange.direct","Routing.A",saveRequest);
        return auth;
    }
    public String login(LoginRequestDto dto) {
        Auth auth = authRepository.findOptionalByEmailAndPassword(dto.getEmail(),dto.getPassword())
                .orElseThrow(() -> new AuthMicroServiceException(ErrorType.EMAIL_OR_PASSWORD_WRONG));
        if(!auth.getStatus().equals(EStatus.ACTIVE)) throw new AuthMicroServiceException(ErrorType.ACOOUNT_NOT_ACTIVE);

        return createToken(auth.getId(), auth.getRole());
    }
    @Transactional
    public String activateCode(ActivateCodeRequestDto dto) {
        Auth auth = authRepository.findById(dto.getId()).orElseThrow(() -> new AuthMicroServiceException(ErrorType.USER_NOT_FOUND));
        if(!auth.getActivationCode().equals(dto.getActivationCode())) throw new AuthMicroServiceException(ErrorType.ACTIVATION_CODE_MISMATCH);
        return statusControl(auth);
    }

    @Transactional
    public String statusControl(Auth auth){
        switch (auth.getStatus()) {
            case ACTIVE:
                throw new AuthMicroServiceException(ErrorType.USER_ALREADY_ACTIVE);
            case DELETED:
            case BANNED:
                throw new AuthMicroServiceException(ErrorType.ACCESS_DENIED);
            case PENDING:
                auth.setStatus(EStatus.ACTIVE);
                authRepository.save(auth);
                String token = jwtTokenManager.createToken(auth.getId(),auth.getStatus()).orElseThrow(()-> new AuthMicroServiceException(ErrorType.TOKEN_CREATION_FAILED));
                //userProfileManager.updateStatus(jwtTokenManager.createToken(auth.getId(),auth.getStatus()).orElseThrow(()-> new AuthMicroServiceException(ErrorType.TOKEN_CREATION_FAILED)));

                rabbitTemplate.convertAndSend("exchange.direct","Routing.B",token);
                return "Activation Success!";
            default:
                throw new AuthMicroServiceException(ErrorType.ACTIVATION_ERROR);
        }
    }
    @Transactional
    public String softDelete(Long authId) {
        Auth auth = authRepository.findById(authId).orElseThrow(() -> new AuthMicroServiceException(ErrorType.USER_NOT_FOUND));
        if(auth.getStatus().equals(EStatus.DELETED)) throw new AuthMicroServiceException(ErrorType.USER_ALREADY_DELETED);
        auth.setStatus(EStatus.DELETED);
        authRepository.save(auth);
        userProfileManager.updateStatus(jwtTokenManager.createToken(auth.getId(),auth.getStatus()).orElseThrow(()-> new AuthMicroServiceException(ErrorType.TOKEN_CREATION_FAILED)));
        return "Id = " + authId + " : Successfully Deleted";
    }

    public String createToken(Long id) {
        if(authRepository.findById(id).isEmpty()) throw new AuthMicroServiceException(ErrorType.ID_NOT_EXIST);
        return jwtTokenManager.createToken(id).orElseThrow(() -> new AuthMicroServiceException(ErrorType.TOKEN_CREATION_FAILED));
    }

    public String createToken(Long id,ERole role) {
        return jwtTokenManager.createToken(id, role).orElseThrow(() -> new AuthMicroServiceException(ErrorType.TOKEN_CREATION_FAILED));
    }

    public Long getIdFromToken(String token) {
        return jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new AuthMicroServiceException(ErrorType.ID_NOT_FOUND));
    }

    public String getRoleFromToken(String token) {
        return jwtTokenManager.getRoleFromToken(token).orElseThrow(() -> new AuthMicroServiceException(ErrorType.ID_NOT_FOUND)).toString();
    }

    //@RabbitListener(queues ="q.B")
    public void updateEmail(String token) {
        Long id = getIdFromToken(token);
        String email = jwtTokenManager.getEmailFromToken(token).orElseThrow(() -> new AuthMicroServiceException(ErrorType.EMAIL_NOT_FOUND));
        Auth auth = authRepository.findById(id).orElseThrow(() -> new AuthMicroServiceException(ErrorType.USER_NOT_FOUND));
        auth.setEmail(email);
        authRepository.save(auth);
    }
}
