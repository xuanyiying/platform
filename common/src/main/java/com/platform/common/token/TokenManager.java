package com.platform.common.token;

/**
 * @author wangying
 */
public interface TokenManager {
    /**
     * Generate token by userName and userId and password.
     *
     * @param userId
     * @param username
     * @return token
     */
    String generateToken(Integer userId, String username);

    /**
     * Verify and parse token.
     *
     * @param token
     * @return "" userId
     */
    Integer verifyAndParseToken(String token);

    /**
     * @param id
     */
    void removeToken(Integer id);

}
