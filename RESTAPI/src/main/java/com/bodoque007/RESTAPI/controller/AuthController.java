package com.bodoque007.RESTAPI.controller;

import com.bodoque007.RESTAPI.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Operation(summary = "Get JWT Token", description = "Generates a JWT token for the authenticated user.")
    @ApiResponse(responseCode = "200", description = "JWT Token generated successfully",
            content = @Content(mediaType = "text/plain", schema = @Schema(type = "string")))
    @PostMapping("/token")
    public String token(Authentication authentication) {
        LOG.debug("Token requested for user: '{}'", authentication.getName());

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        String name = authentication.getName();

        String token = tokenService.generateToken(name, scope);
        LOG.debug("Token granted: {}", token);
        return token;
    }
}