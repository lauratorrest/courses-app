package com.company.courses.authentication.shared.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtUtil {

    public static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static final String AUTHORIZATION = "Authorization";
    public static final long EXPIRE_ACCESS_TOKEN = 1000L * 60 * 60 * 24; // 1 day
    public static final String PREFIX = "Bearer";
}
