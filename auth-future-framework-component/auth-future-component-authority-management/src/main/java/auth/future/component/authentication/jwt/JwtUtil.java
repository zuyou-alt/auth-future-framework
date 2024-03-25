package auth.future.component.authentication.jwt;

import auth.future.component.redis.SerializeUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * token操作
 * @author hzy
 * @since 2023-08-16
 **/
public class JwtUtil {

    public static final String SECRET = "404E63526651111156A586E3272357538782F413F4428472B4B6250645367566B5970";

    /**
     * 解析获取用户账号
     * @param token 用户token
     * @return 用户账号
     */
    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 生成token
     * @param claims 设置JWT的有效载荷，如果不希望JWT的正文为JSON，而是希望它是纯文本字符串，请使用 setPayload(String) 方法
     * @param subject 设置 JWT 声明 sub （主题）值
     * @param expiration 设置JWT的过期时间，单位秒
     * @return token字符串
     */
    public static String generateToken(Map<String, Object> claims, String subject, long expiration ) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 生成token
     * @param payload 设置JWT的有效载荷，如果不希望JWT的正文为JSON，而是希望它是纯文本字符串，请使用 setPayload(String) 方法
     * @return token字符串
     */
    public static String generateToken(String payload){
        return Jwts.builder()
                .setPayload(payload)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }


    /**
     * 获取token载荷
     */
    public static String extractAllClaimString(String token,String secret) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parsePlaintextJws(token)
                .getBody();
    }

    /**
     * 检测token是否过期
     * true 已过期  false未过期
     */
    private static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * 获取token过期时间
     */
    private static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 获取token载荷
     */
    private static Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    /**
     * 获取token载荷
     */
    public static  <T extends Serializable> T extractAllClaimObject(String token, Class<T> clazz) {
        Claims claims = extractAllClaims(token);
        JSONObject entries = JSONUtil.parseObj(claims);
        return JSONUtil.toBean(entries,clazz);
    }


    /**
     * 生成token秘钥
     */
    private static Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
