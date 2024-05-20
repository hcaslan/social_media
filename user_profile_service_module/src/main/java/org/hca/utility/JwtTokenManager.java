package org.hca.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.hca.entity.EStatus;
import org.hca.exception.ErrorType;
import org.hca.exception.UserProfileServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenManager {
    @Value("${userprofileservice.secret.secret-key}")
    String secretKey;
    @Value("${userprofileservice.secret.issuer}")
    String issuer;
    Long expireTime = 1000L * 60*15; // 15dakikalık bir zaman

    public Optional<String> createToken(Long id, String email) {
        String token = "";
        try {
            token = JWT.create().withAudience()
                    .withClaim("id",
                            id)
                    .withClaim("email", email)
                    .withClaim("whichpage",
                            "AuthMicroService")
                    .withClaim("ders",
                            "Java JWT")
                    .withClaim("grup",
                            "Java14")
                    .withIssuer(issuer)
                    .withIssuedAt(new Date()) //Tokenın yaratıldığı an.
                    .withExpiresAt(new Date(System.currentTimeMillis() + expireTime)) //Data,Instant
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);
        }
        catch (IllegalArgumentException e) {
            throw new UserProfileServiceException(ErrorType.TOKEN_CREATION_FAILED);
        }
        catch (JWTCreationException e) {
            throw new UserProfileServiceException(ErrorType.TOKEN_CREATION_FAILED);
        }
    }
    public Optional<String> createTokenForUserId(Long id, String userId) {
        String token = "";
        try {
            token = JWT.create().withAudience()
                    .withClaim("id",
                            id)
                    .withClaim("userId", userId)
                    .withClaim("whichpage",
                            "AuthMicroService")
                    .withClaim("ders",
                            "Java JWT")
                    .withClaim("grup",
                            "Java14")
                    .withIssuer(issuer)
                    .withIssuedAt(new Date()) //Tokenın yaratıldığı an.
                    .withExpiresAt(new Date(System.currentTimeMillis() + expireTime)) //Data,Instant
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);
        }
        catch (IllegalArgumentException e) {
            throw new UserProfileServiceException(ErrorType.TOKEN_CREATION_FAILED);
        }
        catch (JWTCreationException e) {
            throw new UserProfileServiceException(ErrorType.TOKEN_CREATION_FAILED);
        }
    }

    public Optional<EStatus> getStatusFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if(decodedJWT==null)
                return Optional.empty();

            String status = decodedJWT.getClaim("status").asString();
            return Optional.of(EStatus.valueOf(status));
        }
        catch (IllegalArgumentException e) {
            throw new UserProfileServiceException(ErrorType.TOKEN_ARGUMENT_NOTVALID);
        }
        catch (JWTVerificationException e) {
            throw new UserProfileServiceException(ErrorType.TOKEN_VERIFY_FAILED);
        }

    }
    public Optional<Long> getAuthIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT == null)
                return Optional.empty();

            Long id = decodedJWT.getClaim("id").asLong();
            return Optional.of(id);
        } catch (IllegalArgumentException e) {
            throw new UserProfileServiceException(ErrorType.TOKEN_ARGUMENT_NOTVALID);
        } catch (JWTVerificationException e) {
            throw new UserProfileServiceException(ErrorType.TOKEN_VERIFY_FAILED);
        }
    }
}