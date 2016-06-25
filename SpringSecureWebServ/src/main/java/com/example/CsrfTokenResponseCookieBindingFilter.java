package com.example;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

public class CsrfTokenResponseCookieBindingFilter extends OncePerRequestFilter {

	protected static final String REQUEST_ATTRIBUTE_NAME = "_csrf";
	
	Logger log = Logger.getLogger(this.getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException 
	{
		log.info("=== Internal Filter ===");

		CsrfToken token = (CsrfToken) request.getAttribute(REQUEST_ATTRIBUTE_NAME);

		Cookie cookie = new Cookie(CSRF.RESPONSE_COOKIE_NAME, token.getToken());
		cookie.setPath("/");

		response.addCookie(cookie);

		filterChain.doFilter(request, response);
	}
}
