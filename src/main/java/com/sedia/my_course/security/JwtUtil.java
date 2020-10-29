package com.sedia.my_course.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sedia.my_course.dto.LoginUser;
import com.sedia.my_course.entity.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Date;
import java.util.List;

public class JwtUtil {
	private static final long EXPIRATION_TIME = 432_000_000;     // 5天
	private static final String TOKEN_PREFIX = "Bearer";        // Token前缀
	private static final String HEADER_STRING = "Authorization";// 存放Token的Header Key
	private static final Key key = MacProvider.generateKey();  //給定一組密鑰，用來解密以及加密使用
	private static final ObjectMapper MAPPER = new ObjectMapper();

	// JWT產生方法
	public static void addAuthentication(HttpServletResponse response, Authentication auth) {
		// 生成JWT
		String jwt = Jwts.builder()
		.setSubject(auth.getName())
		.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
		.signWith(key).compact();
		// 把JWT傳回response
		try {
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_OK);
			User user = (User) auth.getPrincipal();
			LoginUser loginUser = LoginUser.builder().id(user.getUserId()).username(user.getUsername()).jwt(jwt).build();
			response.getOutputStream().println(MAPPER.writeValueAsString(loginUser));
			System.out.println("login ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// JWT驗證方法
	public static Authentication getAuthentication(HttpServletRequest request) {
		// 從request的header拿回token
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// 解析 Token
			try {
				Claims claims = Jwts.parser()
				// 驗證
				.setSigningKey(key)
				// 去掉 Bearer
				.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
				.getBody();

				// 拿用户名
				String username = claims.getSubject();
				// 得到權限
				List<GrantedAuthority> authorities =
				AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorize"));
				// 返回Token
				return StringUtils.hasText(username) ?
				new UsernamePasswordAuthenticationToken(username, null, authorities) :
				null;
			} catch (JwtException ex) {
				System.out.println(ex);
			}
		}
		return null;
	}
}