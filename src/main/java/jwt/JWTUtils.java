package jwt;

import java.util.Date;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {

	final static String SECRET = "batman";

	public static String createToken(String email) {

		JwtBuilder jwtBuilder = Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000)) // 30 min
				.signWith(SignatureAlgorithm.HS512, SECRET);

		return jwtBuilder.compact();
	}

}
