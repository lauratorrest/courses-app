package com.company.courses.authentication.shared.utils;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

public class JwtUtil {
    public static final String AUTHORIZATION = "Authorization";
    public static final long EXPIRE_ACCESS_TOKEN = 1000L * 60 * 60 * 24; // 1 day
    public static final String PREFIX = "Bearer";
    private static final String SECRET = "secret";
    public static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));
    private JwtUtil() {
    }
}
