package com.desafiobackendviceri.todoapi.resource;

import com.desafiobackendviceri.todoapi.dto.request.Login;
import com.desafiobackendviceri.todoapi.dto.request.Session;
import com.desafiobackendviceri.todoapi.entity.User;
import com.desafiobackendviceri.todoapi.repository.UserRepository;
import com.desafiobackendviceri.todoapi.security.JWTCreator;
import com.desafiobackendviceri.todoapi.security.JWTObject;
import com.desafiobackendviceri.todoapi.security.SecurityConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginResource {

    private PasswordEncoder encoder;
    private SecurityConfig securityConfig;
    private UserRepository repository;

    @PostMapping("api/v1/login")
    public Session logar(@RequestBody Login login){
        Optional<User> user = repository.findUserByEmail(login.getEmail());
        if(user!=null&& user.isPresent()) {
            User userReturned = user.get();
            boolean passwordOk =  encoder.matches(login.getPassword(), userReturned.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Invalid password to login: " + login.getEmail());
            }
            Session session = new Session();
            session.setLogin(userReturned.getEmail());
            JWTObject jwtObject = new JWTObject();
            jwtObject.setEmail(userReturned.getEmail());
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(userReturned.getRoles());
            session.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return session;
        }else {
            throw new RuntimeException("Error to try and login");
        }
    }
}
