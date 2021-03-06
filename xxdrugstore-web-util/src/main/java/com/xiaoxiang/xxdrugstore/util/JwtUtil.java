package com.xiaoxiang.xxdrugstore.util;

import io.jsonwebtoken.*;

import java.util.Map;

public class JwtUtil {

    public static String encode(String key, Map<String,Object> param, String salt){
        //正常情况下，key是服务器密钥，salt盐值由浏览器生成

        if(salt!=null){
            key+=salt;
        }
        JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.HS256,key);

        jwtBuilder = jwtBuilder.setClaims(param);

        String token = jwtBuilder.compact();
        return token;

    }


    public  static Map<String,Object>  decode(String token ,String key,String salt){
        Claims claims=null;
        if (salt!=null){
            key+=salt;
        }
        try {
            claims= Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch ( JwtException e) {
           return null;
        }
        return  claims;
    }
}
