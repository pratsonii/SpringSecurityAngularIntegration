package com.example;


import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * An authentication entry point implementation adapted to a REST approach.
 */
public class RESTAuthenticationEntryPoint implements AuthenticationEntryPoint {

	Logger log = Logger.getLogger(this.getClass());
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
	                     AuthenticationException authException) throws IOException, ServletException {

		log.info("=== REST Entry Point ===");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
}
