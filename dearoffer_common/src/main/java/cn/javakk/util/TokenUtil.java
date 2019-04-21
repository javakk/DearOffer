package cn.javakk.util;



import cn.javakk.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @Author: javakk
 * @Date: 2019/4/21 15:26
 */
public class TokenUtil {


    private static String key = "javakk1024";

    private static long ttl = 60480000;

    /**
     * 生成token
     * @param id
     * @param subject
     * @param user
     * @return
     */
    public static String createToken(String id, String subject, User user) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, key)
                .claim("user", user);
        if (ttl > 0) {
            builder.setExpiration( new Date( nowMillis + ttl));
        }
        return builder.compact();
    }


    /**
     * 解析token
     * @param token
     * @return
     */
    public static Claims parseToken(String token){
        return  Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    public static void destroyToken(String id) {

    }

    public static void main(String[] args) {
//        String ad = createToken("123", "ad", new User());
        Claims claims = parseToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMiLCJzdWIiOiJhZCIsImlhdCI6MTU1NTgzMzQ3OCwidXNlciI6eyJpZCI6bnVsbCwicm9sZUlkIjpudWxsLCJ1c2VyTmFtZSI6bnVsbCwicGFzc3dvcmQiOm51bGwsImVtYWlsIjpudWxsLCJwaG9uZSI6bnVsbCwiaGVhZEltYWdlIjpudWxsLCJzZXgiOm51bGwsInNhbHQiOm51bGwsInN0YXR1cyI6bnVsbCwiY3JlYXRlVGltZSI6bnVsbCwibW9kaWZ5VGltZSI6bnVsbH0sImV4cCI6MTU1NTg5Mzk1OH0.fNpMpiOxC42ysI3OTCkN5FG1LiX4ycodVnRGGcd_JL0");
        System.out.println(claims.get("user"));
    }

}

