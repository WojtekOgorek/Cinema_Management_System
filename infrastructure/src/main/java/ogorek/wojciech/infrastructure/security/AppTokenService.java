package ogorek.wojciech.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.user.UserFunctors;
import ogorek.wojciech.domain.model.user.repository.UserRepository;
import ogorek.wojciech.infrastructure.security.dto.AuthenticationDto;
import ogorek.wojciech.infrastructure.security.dto.AuthorizedUserDto;
import ogorek.wojciech.infrastructure.security.dto.TokensDto;
import ogorek.wojciech.service.services.exceptions.AppTokensServiceException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AppTokenService {


    @Value("${tokens.access-token.expiration-time-ms}")
    private Long accessTokenExpirationTimeMs;

    @Value("${tokens.refresh-token.expiration-time-ms}")
    private Long refreshTokenExpirationTimeMs;

    @Value("${tokens.refresh-token.property}")
    private String refreshTokenProperty;

    @Value("${tokens.prefix}")
    private String tokensPrefix;

    private final UserRepository userRepository;

    private final SecretKey secretKey;

    /*
    *
    * ---- GENERATING TOKENS -----
    *
    */

    public TokensDto generateTokens(AuthenticationDto authenticationDto) {
        if (authenticationDto == null) {
            throw new AppTokensServiceException("Authentication dto is null");
        }

        var userId = userRepository
                .getUserByUsername(authenticationDto.getUsername())
                .map(UserFunctors.toId)
                .orElseThrow(() -> new AppTokensServiceException("Cannot find user by username"));

        var currentDate = new Date();
        var accessTokenExpirationDate = new Date(System.currentTimeMillis() + accessTokenExpirationTimeMs);
        var refreshTokenExpirationDate = new Date(System.currentTimeMillis() + refreshTokenExpirationTimeMs);

        var accessToken = Jwts
                .builder()
                .setSubject(String.valueOf(userId))
                .setExpiration(accessTokenExpirationDate)
                .setIssuedAt(currentDate)
                .signWith(secretKey)
                .compact();

        var refreshToken = Jwts
                .builder()
                .setSubject(String.valueOf(userId))
                .setExpiration(accessTokenExpirationDate)
                .setIssuedAt(currentDate)
                .claim(refreshTokenProperty, refreshTokenExpirationDate.getTime())
                .signWith(secretKey)
                .compact();

        return TokensDto
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }
    /*
    *
    * ---- PARSING TOKENS ----
    *
    */

    public AuthorizedUserDto parseToken(String header){
        return AuthorizedUserDto.builder().build();
    }

    private Claims claims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Long id (String token){
        return Long.parseLong(claims(token).getSubject());
    }

    private Date expirationDate(String token){
        return claims(token).getExpiration();
    }

    private boolean isTokenValid(String token){
        return expirationDate(token).after(new Date());
    }


}
