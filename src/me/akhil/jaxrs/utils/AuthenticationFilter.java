package me.akhil.jaxrs.utils;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter{

	public static final String AUTHENTICATION_HEADER = "Authorization";
	
	@Override
	public void destroy() {		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
	
		if(request instanceof HttpServletRequest){
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			String authHeader = httpServletRequest.getHeader(AUTHENTICATION_HEADER);
			if (!authenticate(authHeader)) {
				if (response instanceof HttpServletResponse) {
					HttpServletResponse httpServletResponse = (HttpServletResponse) response;
					httpServletResponse
							.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}
			}else{
				filterChain.doFilter(request, response);
			}
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	private boolean authenticate(String authHeader){
		if (null == authHeader){
			return false;
		}
		final String encodedUserPassword = authHeader.replaceFirst("Basic"
				+ " ", "");
		
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(
					encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();
		return "admin".equals(username)
				&& "admin".equals(password);		
	}

}
