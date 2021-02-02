package com.example.demo.myUtile.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtilty
{
    private final String CLAIMS_SUBJECT = "sub";
    private final String CLAIMS_CREATED = "created";

    private long TOKEN_VALIDITY = 604800L;
    private String TOKEN_SECRET = "CarSecret443";
    private String TOKEN_HEADER = "Authorization";

    private Date generateExpirationDate()
    {
        return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
    }

    public String generateToken(UserDetails userDetails)
    {
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIMS_SUBJECT,userDetails.getUsername());
        claims.put(CLAIMS_CREATED,new Date());

        return Jwts.builder().setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512 , TOKEN_SECRET)
                .compact();
    }

    private Claims getClaims(String token)
    {
        Claims claims;
        try{
            claims =  Jwts.parser().setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (Exception ex)
        {
            claims = null;
        }
        return claims;
    }

    public String getUsernameFromToken(String token)
    {
        try{
            Claims claims =  getClaims(token);

            return claims.getSubject();
        }
        catch (Exception ex)
        {
            return null;
        }

    }

    public boolean isTokenVaild(String token , UserDetails userDetails)
    {
        String uesrname = getUsernameFromToken(token);
        return ( uesrname.equals(userDetails.getUsername()) && !isTokenExpired(token) );
    }

    public boolean isTokenExpired(String token)
    {
        Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

}
