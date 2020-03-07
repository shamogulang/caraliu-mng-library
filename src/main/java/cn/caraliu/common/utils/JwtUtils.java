package cn.caraliu.common.utils;

import cn.caraliu.exception.InvalidJwtException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class JwtUtils {

    private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private static String SECRET = "c42600766b11abc2684a7d486ce2b7d3";
    private static String ISSUER = "cn.mng.caraliu";

    /**
     * 获取盐的操作
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    public static String encrypt(String text, byte[] salt) {
        String encryptedText = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < bytes.length; ++i) {
                sb.append(Integer.toString((bytes[i] & 255) + 256, 16).substring(1));
            }

            encryptedText = sb.toString();
        } catch (NoSuchAlgorithmException var7) {
            var7.printStackTrace();
        }
        return encryptedText;
    }

    public static String generateJWT(String id, long periodInSecond, Map<String, String> map) throws JOSEException {
        JWSSigner signer = new MACSigner(SECRET.getBytes());
        JWTClaimsSet.Builder builder = (new JWTClaimsSet.Builder()).issuer(ISSUER).subject(id).issueTime(new Date());
        if (periodInSecond >= 0L) {
            builder.expirationTime(new Date((new Date()).getTime() + periodInSecond * 1000L));
        }

        if (map != null) {
            Iterator var6 = map.entrySet().iterator();
            while(var6.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry)var6.next();
                builder.claim((String)entry.getKey(), entry.getValue());
            }
        }

        JWTClaimsSet claimsSet = builder.build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }

    public static JWTClaimsSet parseJWT(String jwt) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(jwt);
            if (!signedJWT.verify(new MACVerifier(SECRET.getBytes()))) {
                return null;
            } else {
                JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();
                if (jwtClaimsSet == null) {
                    return null;
                } else {
                    return !jwtClaimsSet.getIssuer().equals(ISSUER) ? null : signedJWT.getJWTClaimsSet();
                }
            }
        } catch (Exception e) {
            logger.error("parseJwt error, jwt => "+jwt,e);
            return null;
        }
    }

    public static String validateUserAndReturnToken(HttpServletRequest request)  {
        String userPk = HeaderUtils.getRequestHeaderValue(request, HeaderUtils.X_CARALIU_USER_PK);
        String authorization = HeaderUtils.getRequestHeaderValue(request, HeaderUtils.AUTHORIZATION);
        JWTClaimsSet cs = isAuthenticatedUser(userPk, authorization);
        if (cs == null) {
            throw new InvalidJwtException();
        } else {
            try {
                return cs.getStringClaim("userToken");
            }catch (Exception e){
                logger.error("getStringClaim jwtToken error,for details:",e);
            }
        }
        return null;
    }

    public static JWTClaimsSet isAuthenticatedUser(String userPkOrId, String authorization) {
        if (userPkOrId == null || "".equals(userPkOrId) || authorization == null || "".equals(authorization)) {
            return null;
        } else {
            // 获取的同时检验
            JWTClaimsSet cs = getClaimSet(authorization);
            if (cs != null) {
                String subject = cs.getSubject();
                if (subject != null && subject.equals(userPkOrId.trim())) {
                    return cs;
                }
            }
            return null;
        }
    }

    private static JWTClaimsSet getClaimSet(String authorizationHeaderValue) {
        String jwt = getJWT(authorizationHeaderValue);
        return jwt != null ? parseJWT(jwt) : null;
    }

    private static String getJWT(String value) {
        if (value != null) {
            String trimString = value.trim();
            if (trimString.startsWith("Bearer")) {
                String jwt = trimString.substring("Bearer".length());
                if (jwt != null) {
                    return jwt.trim();
                }
            }
        }
        return null;
    }

}
