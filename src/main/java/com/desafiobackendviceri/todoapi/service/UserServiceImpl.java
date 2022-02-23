package com.desafiobackendviceri.todoapi.service;

import com.desafiobackendviceri.todoapi.dto.request.UserDto;
import com.desafiobackendviceri.todoapi.dto.response.MessageResponseDto;
import com.desafiobackendviceri.todoapi.entity.User;
import com.desafiobackendviceri.todoapi.exception.ResourceAlreadyExistent;
import com.desafiobackendviceri.todoapi.exception.UnsafePasswordException;
import com.desafiobackendviceri.todoapi.mapper.UserMapper;
import com.desafiobackendviceri.todoapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final UserMapper mapper = UserMapper.INSTANCE;
    private final PasswordEncoder encoder;


    @Override
    public MessageResponseDto createUser(UserDto userDto) {
        Optional<User> email = repository.findUserByEmail(userDto.getEmail());
        if (email.isPresent()) {
            throw new ResourceAlreadyExistent("The email informed already exists on database");
        }
        List<String> roles;
        if (userDto.getRoles()==null){
            roles = new ArrayList<>();
        }else{
            roles = new ArrayList<>(userDto.getRoles());
        }
        roles.add("USERS");
        userDto.setRoles(roles);
        if (verifyPasswordSecurity(userDto.getPassword())){
            String pass = userDto.getPassword();
            userDto.setPassword(encoder.encode(pass));
            User userToSave = mapper.toModel(userDto);
            User savedUser = repository.save(userToSave);
            MessageResponseDto message = new MessageResponseDto();
            return message.createdMessageResponse(savedUser.getId(), "Created user with id ");
        }
        return null;

    }

    private boolean verifyPasswordSecurity(String password) {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String number = "0123456789";
        String specialChar = "!@#$%^&*()-+.,";
        verifyIfHasNumber(password,number);
        verifyIfHasUpperCase(password,upperCase);
        verifyIfHasLowerCase(password,lowerCase);
        verifyIfHasSpecialChar(password,specialChar);
        if(password.length()<8){
            throw new UnsafePasswordException("The password informed must to have a minimum size of 8 characters.");
        }
        return true;
    }

    private boolean verifyIfHasSpecialChar(String password, String specialChar) {
        boolean returnValue = false;
        for (int i=0; i<password.length();i++){
            String caracter = String.valueOf(password.charAt(i));
            if (specialChar.contains(caracter)){
                returnValue = true;
                break;
            }
        }
        if (returnValue==false){
            throw new UnsafePasswordException("The password informed must to have a special character.");
        }
        return returnValue;
    }

    private boolean verifyIfHasLowerCase(String password, String lowerCase) {
        boolean returnValue = false;
        for (int i=0; i<password.length();i++){
            String caracter = String.valueOf(password.charAt(i));
            if (lowerCase.contains(caracter)){
                returnValue = true;
                break;
            }
        }
        if(returnValue==false){
            throw new UnsafePasswordException("The password informed must to have a lowercase.");
        }
        return returnValue;
    }

    private boolean verifyIfHasUpperCase(String password, String upperCase) {
        boolean returnValue = false;
        for (int i=0; i<password.length();i++){
            String caracter = String.valueOf(password.charAt(i));
            if (upperCase.contains(caracter)){
                returnValue = true;
                break;
            }
        }
        if(returnValue==false){
            throw new UnsafePasswordException("The password informed must to have a uppercase.");
        }
        return returnValue;
    }

    private boolean verifyIfHasNumber(String password, String number) {
        boolean returnValue = false;
        for (int i=0; i<password.length();i++){
            String caracter = String.valueOf(password.charAt(i));
            if (number.contains(caracter)) {
                returnValue = true;
                break;
            }
        }if (returnValue=false) {
            throw new UnsafePasswordException("The password informed must to have a number.");
        }
        return returnValue;
    }

    @Override
    public Long findIdUser(Object principal) {
        Long id = null;
        String email =principal.toString();
        Optional<User>userReturned= repository.findUserByEmail(email);
        if (userReturned.isPresent()){
            id = userReturned.get().getId();
        }
        return id;
    }
}
