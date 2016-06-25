package com.example;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * An authentication success handler implementation adapted to a REST approach.
 */
public class RESTAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//	                                    Authentication authentication) throws IOException, ServletException {
//
//		clearAuthenticationAttributes(request);
//	}
	Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException 
	{
		log.info("=== REST Authentication Success ===");

		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

}
