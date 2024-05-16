package org.hca.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hca.dto.request.ActivateCodeRequestDto;
import org.hca.dto.request.LoginRequestDto;
import org.hca.dto.request.RegisterRequestDto;
import org.hca.dto.response.RegisterResponseDto;
import org.hca.entity.ERole;
import org.hca.mapper.AuthMapper;
import org.hca.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.hca.constant.EndPoints.*;


@RestController
@RequestMapping(ROOT+ AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto) {
        return ResponseEntity.ok(AuthMapper.INSTANCE.authToRegisterDto(authService.register(dto)));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PutMapping(ACTIVATECODE)
    public ResponseEntity<String> activateCode(@RequestBody ActivateCodeRequestDto dto) {
        return ResponseEntity.ok(authService.activateCode(dto));
    }

    @DeleteMapping(DELETE + "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long authId) {
        return ResponseEntity.ok(authService.softDelete(authId));
    }
    @PutMapping("/updateEmail")
    public ResponseEntity<Void> updateEmail(@RequestHeader("Authorization") String token){
        authService.updateEmail(token);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getToken")
    public ResponseEntity<String> getToken(@RequestParam Long id){
        return ResponseEntity.ok(authService.createToken(id));
    }
    @GetMapping("/getRoleToken")
    public ResponseEntity<String> getRoleToken (@RequestParam Long id, @RequestParam ERole role){
        return ResponseEntity.ok(authService.createToken(id,role));
    }

    @GetMapping("/getIdFromToken")
    public ResponseEntity<Long> getIdFromToken (@RequestParam("token")String token){
        return ResponseEntity.ok(authService.getIdFromToken (token));
    }

    @GetMapping("/getRoleFromToken")
    public ResponseEntity<String> getRoleFromToken (@RequestParam("token")String token){
        return ResponseEntity.ok(authService.getRoleFromToken (token));
    }
}