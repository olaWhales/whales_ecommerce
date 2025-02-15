package com.olawhales.whales_ecommerce.SecurityConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private String secreteKey = ""; // i just outcome this key here to put in the method below
    // i later cancelled this line above because it is out codding
    //this is a wy to generate the token
    public JwtService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHa256");
            SecretKey sk = keyGen.generateKey();
            secreteKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    // this is the class we generate a token for each user logging
    public String GenerateToken(String username) {

        //we use map and the object here
        Map<String , Object> claims = new HashMap<>(); // at this point it is empty
        // To  really generate the token we use/import a class called jwts class
        return Jwts.builder()  //we use Jwts i import the token
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10 ))
                .and()
                .signWith(getkey()) // i generate a key here
                .compact();
    }

    private SecretKey getkey() {
        byte[] keyByte = Decoders.BASE64.decode(secreteKey);
        return Keys.hmacShaKeyFor(keyByte);
        // by returning secrete alone doesn't work so we need to convert it to byte ARRAY first
        // this is actually out codding so we didn't use it again
    }


    public String extractUsername(String token) {
        return extractClaim(token , Claims::getSubject);
    }
    private <T> T extractClaim(String token , Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getkey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token , Claims::getExpiration);
    }

}
