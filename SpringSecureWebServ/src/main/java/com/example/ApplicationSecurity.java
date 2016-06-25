package com.example;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

	@Resource
	private AuthenticationEntryPoint authenticationEntryPoint;
	@Resource
	private AuthenticationFailureHandler authenticationFailureHandler;
	@Resource
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	@Resource
	private CORSFilter corsFilter;
	@Resource
	private LogoutSuccessHandler logoutSuccessHandler;
	

    @Autowired
    private UserDetailsService userDetailsService;

	Logger log = Logger.getLogger(this.getClass());
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS, "/*/**").permitAll()
			.antMatchers("/login", "/rest/open/**").permitAll()
			.antMatchers("/logout", "/rest/**").authenticated();

		// Handlers and entry points
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		http.formLogin().successHandler(authenticationSuccessHandler);
		http.formLogin().failureHandler(authenticationFailureHandler);

		// Logout
		http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);

		// CORS
		http.addFilterBefore(corsFilter, ChannelProcessingFilter.class);

		// CSRF
		http.csrf().requireCsrfProtectionMatcher(
			new AndRequestMatcher(
				// Apply CSRF protection to all paths that do NOT match the ones below

				// We disable CSRF at login/logout, but only for OPTIONS methods
				new NegatedRequestMatcher(new AntPathRequestMatcher("/login*/**", HttpMethod.OPTIONS.toString())),
				new NegatedRequestMatcher(new AntPathRequestMatcher("/logout*/**", HttpMethod.OPTIONS.toString())),

				new NegatedRequestMatcher(new AntPathRequestMatcher("/rest*/**", HttpMethod.GET.toString())),
				new NegatedRequestMatcher(new AntPathRequestMatcher("/rest*/**", HttpMethod.HEAD.toString())),
				new NegatedRequestMatcher(new AntPathRequestMatcher("/rest*/**", HttpMethod.OPTIONS.toString())),
				new NegatedRequestMatcher(new AntPathRequestMatcher("/rest*/**", HttpMethod.TRACE.toString())),
				new NegatedRequestMatcher(new AntPathRequestMatcher("/rest/open*/**"))
			)
		);
		http.addFilterAfter(new CsrfTokenResponseCookieBindingFilter(), CsrfFilter.class); // CSRF tokens handling
	}

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	log.info("====== In Authentication Config ===");
    	try
    	{
    		auth.userDetailsService(userDetailsService);
    	}
    	catch(IllegalArgumentException e)
    	{
    		System.out.println("illegal Arg");
    	}
    }
}
