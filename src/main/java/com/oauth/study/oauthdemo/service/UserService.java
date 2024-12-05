package com.oauth.study.oauthdemo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.oauth.study.oauthdemo.dao.OauthRepository;
import com.oauth.study.oauthdemo.domain.OauthUserEntity;

@Service
public class UserService extends DefaultOAuth2UserService{
    
    @Autowired
    private OauthRepository oauthRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("debug >>> UserService loadUser userRequest : " + userRequest);
        
        Map<String, Object> attributes = super.loadUser(userRequest).getAttributes();
        System.out.println("debug >>> UserService attributes : " + attributes.toString());

        String email = null;
        String oauthType = userRequest.getClientRegistration().getRegistrationId();
        System.out.println("debug >>> UserService oauthType : " + oauthType);
        
//        OAuth2User user = super.loadUser(userRequest);

        if("kakao".equalsIgnoreCase(oauthType.toLowerCase())){
            System.out.println("debug >>> UserService oauthType kakao");
            email = ((Map<String,Object>)attributes.get("kakao_account")).get("email").toString();
            System.out.println("debug >>> UserService email : " + email);
        }
        // User 존재여부 확인 및 없으면 생성
        if(getUserByEmailAndType(email, oauthType) == null){
            System.out.println("debug >>> User Not Register: " + email);
            OauthUserEntity entity = OauthUserEntity.builder()
                                                    .email(email)
                                                    .oauthType(oauthType)
                                                    .build();

            oauthRepository.save(entity);                
        }

        return super.loadUser(userRequest);
    }

    public OauthUserEntity getUserByEmailAndType(String email, String oauthType) {
        System.out.println("debug >>> UserService getUserByEmailAndType");
        return oauthRepository.findByEmailAndOauthType(email, oauthType).orElse(null);
    }

}
