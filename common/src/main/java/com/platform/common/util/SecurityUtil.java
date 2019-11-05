package com.platform.common.util;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Decoder;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import org.slf4j.LoggerFactory;

/**
 * Encrypt  and decrypt user password
 *
 * @author wangying
 */
public final class SecurityUtil {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
    private static final String PASSWORD_CRYPT_KEY = "RosettaDB1";
    private final static String DES = "DES";
    private final static String ALGORITHM_SHA1PRNG = "SHA1PRNG";

    private static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom secureRandom = SecureRandom.getInstance(ALGORITHM_SHA1PRNG);
        secureRandom.setSeed(key);
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretkey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.ENCRYPT_MODE, secretkey, secureRandom);
        return cipher.doFinal(src);
    }

    private static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom secureRandom = SecureRandom.getInstance(ALGORITHM_SHA1PRNG);
        secureRandom.setSeed(key);
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretkey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.DECRYPT_MODE, secretkey, secureRandom);
        return cipher.doFinal(src);
    }

    public static String decrypt(String data) {
        try {
            Decoder base64de = Base64.getDecoder();
            byte[] b = base64de.decode(data);
            data = new String(decrypt(hex2byte(b), PASSWORD_CRYPT_KEY.getBytes(StandardCharsets.UTF_8)),
                    StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.warn("Decrypt password met exception,"
                    + " error info: {}", e);
        }
        return data;
    }

    public static String encrypt(String password) {
        if (StringUtil.isNotEmpty(password)) {
            try {
                String encryptStr = byte2hex(encrypt(password.getBytes(StandardCharsets.UTF_8),
                        PASSWORD_CRYPT_KEY.getBytes(StandardCharsets.UTF_8)));
                byte[] bytes = encryptStr.getBytes(StandardCharsets.UTF_8);
                password = new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);
            } catch (Exception e) {
                logger.warn("Encrypt password met exception,"
                        + " error info: {}", e);
            }
        }
        return password;
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder hash = new StringBuilder();
        String temp;
        for (byte aByte : bytes) {
            temp = (Integer.toHexString(aByte & 0XFF));
            if (temp.length() == 1) {
                hash.append("0").append(temp);
            } else {
                hash.append(temp);
            }
        }
        return hash.toString().toUpperCase();
    }

    private static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0) {
            throw new IllegalArgumentException("length is not an even number.");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2, StandardCharsets.UTF_8);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }
}
