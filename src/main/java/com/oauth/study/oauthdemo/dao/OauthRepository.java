package com.oauth.study.oauthdemo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oauth.study.oauthdemo.domain.OauthUserEntity;

@Repository
public interface OauthRepository extends JpaRepository<OauthUserEntity,String>{
    public Optional<OauthUserEntity> findByEmailAndOauthType(String email, String oauthType);    
}