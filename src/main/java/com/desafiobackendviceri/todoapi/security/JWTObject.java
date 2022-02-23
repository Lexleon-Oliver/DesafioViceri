package com.desafiobackendviceri.todoapi.security;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class JWTObject {
    private String email;
    private Date issuedAt;
    private Date expiration;
    private List<String> roles;

}
