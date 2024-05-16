package org.hca.utility;

import org.springframework.stereotype.Component;

@Component
public class TokenManager {
    //1. token üretmeli.
    public String createToken(Long id){
        return "authtoken:"+id;
    }
    //2. ürettiği tokendan bilgi çıkarımı yapmalı:
    // authtoken:2453  buradan geriye 2453 değerini dönmeli:
    public Long getIdFromToken(String token){
        String[] split = token.split("authtoken:");
        return Long.parseLong(split[1]);
    }


}