package com.shun.blog.config.security.custom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			System.out.println("Can't redirect");
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {
		String url = "";
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}

		if (isPLAYER(roles)) {
			url = "/";
		} else if (isCAPTAIN(roles)) {
			url = "/";
		} else if (isAdmin(roles)) {
			url = "/";
		} else if (isSuperAdmin(roles)) {
			url = "/admin/user/list";
		} else {
			url = "/";
		}

		return url;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	private boolean isCAPTAIN(List<String> roles) {
		if (roles.contains("ROLE_CAPTAIN")) {
			return true;
		}
		return false;
	}

	private boolean isAdmin(List<String> roles) {
		if (roles.contains("ROLE_ADMIN")) {
			return true;
		}
		return false;
	}

	private boolean isPLAYER(List<String> roles) {
		if (roles.contains("ROLE_PLAYER")) {
			return true;
		}
		return false;
	}

	private boolean isSuperAdmin(List<String> roles) {
		if (roles.contains("ROLE_SUPERADMIN")) {
			return true;
		}
		return false;
	}
}