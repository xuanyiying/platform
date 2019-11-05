package com.platform.common.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author wangying Created on 2019/10/23.
 */
@Component
public class RedisTokenManager implements TokenManager {
    private static final long EXPIRE_DATE = 60L * 60000;
    private static final String TOKEN_SECRET = "Rosetta-all";
    private static final String TOKEN_ISSUER = "Rosetta";
    private static final Algorithm ALGORITHM = Algorithm.HMAC512(TOKEN_SECRET);
    private static final String USER_ID = "userId";
    private static final String USER_NAME = "username";
    private static final long TOKEN_EXPIRES_HOUR = 60L;
    private RedisTemplate <Integer, String> redisTemplate;
    public RedisTokenManager(@Qualifier("redisTemplate") RedisTemplate <Integer, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer <>(Object.class));
    }

    /**
     * Generate token by userName and userId and password.
     *
     * @param username
     * @return token
     */
    @Override
    public String generateToken(Integer userId, String username) {
        String token = JWT.create().withIssuer(TOKEN_ISSUER).withExpiresAt(new Date(System.currentTimeMillis() +
                EXPIRE_DATE)).withClaim(USER_NAME, username).withClaim(USER_ID, userId).sign(ALGORITHM);
        redisTemplate.boundValueOps(userId).set(token, TOKEN_EXPIRES_HOUR, TimeUnit.SECONDS);
        return token;
    }

    /**
     * Verify and parse token.
     *
     * @param token
     * @return "" userId
     */
    @Override
    public Integer verifyAndParseToken(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM).withIssuer(TOKEN_ISSUER).build();
        return verifier.verify(token).getClaim(USER_ID).asInt();
    }

    @Override
    public void removeToken(Integer id) {
        redisTemplate.delete(id);
    }

}
