package com.intranet.secure.listner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.intranet.secure.model.UserPrincipal;
import com.intranet.secure.service.LoginAttemptService;

@Component
public class AuthenticationSuccessListner {
	private LoginAttemptService loginAttemptService;

    @Autowired
    public AuthenticationSuccessListner(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }
    
    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        if(principal instanceof UserPrincipal) {
            UserPrincipal user = (UserPrincipal) event.getAuthentication().getPrincipal();
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }

}
