package com.oauth.study.oauthdemo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name= "oauth_entity_tbl")
public class OauthUserEntity {
    @Id
    @Column(name = "email", columnDefinition = "VARCHAR(100)", nullable = true)
    private String email;
    
    @Column(name = "oauth_type", columnDefinition = "VARCHAR(50)")
    private String oauthType;
}
