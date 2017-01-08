package br.com.restapp.jwt;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {

	final static String SECRET = "batman";

	public static String createToken(String email) {

		JwtBuilder jwtBuilder = Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // 30 min
				.signWith(SignatureAlgorithm.HS512, SECRET);

		return jwtBuilder.compact();
	}

	public static void validateToken(String token){
		Jws<Claims> claims = Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token);
	}

}
