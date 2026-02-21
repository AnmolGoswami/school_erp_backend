package com.schoolErp.jwt;

import lombok.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {


    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-token-expiration:900000}")  // 15 min
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token-expiration:604800000}") // 7 days
    private long refreshTokenExpiration;

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("tenantId", user.getTenant().getId())
                .claim("roles", user.getRoles().stream().map(Role::getName).toList())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), Jwts.SIG.HS512)
                .compact();
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("tenantId", user.getTenant().getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), Jwts.SIG.HS512)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Long extractTenantId(String token) {
        return extractAllClaims(token).get("tenantId", Long.class);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
