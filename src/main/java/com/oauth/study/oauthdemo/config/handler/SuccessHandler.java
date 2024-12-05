package com.oauth.study.oauthdemo.config.handler;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.oauth.study.oauthdemo.domain.OauthUserEntity;
import com.oauth.study.oauthdemo.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        System.out.println("debug >>> SuccessHandler onAuthenticationSuccess");
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        System.out.println("debug >>> SuccessHandler onAuthenticationSuccess token : " + token);

        String email = null;
        String oauthType = token.getAuthorizedClientRegistrationId();
        System.out.println("debug >>> SuccessHandler oauthType : " + oauthType);

        if("kakao".equalsIgnoreCase(oauthType.toLowerCase())){
            email = ((Map<String,Object>)token.getPrincipal().getAttribute("kakao_account"))
                    .get("email").toString();

            System.out.println("debug >>> UserService email, oauthType : " + email + "\t" + oauthType);
        }
        
        OauthUserEntity user = userService.getUserByEmailAndType(email, oauthType);
        System.out.println("debug >>> SuccessHandler Save Session");
        HttpSession session = request.getSession();
        session.setAttribute("userSession", user);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
