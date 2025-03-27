package com.example.dio.security.util;

import com.example.dio.exception.EligelActivetyException;
import com.example.dio.model.User;
import com.example.dio.repositry.UserRepositry;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserIdentity {

    private final UserRepositry userRepositry;

    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getCurrentUserEmail(){
        return getAuthentication().getName();
    }

    public User getCurrentUser(){
        return userRepositry.findByEmail(this.getCurrentUserEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid User"));
    }

    public void validateOwnerShip(String ownerName){
        if(!getCurrentUser().equals(ownerName))
            throw new EligelActivetyException("User Not Allow To Accsess or modifeyes Resource requested");
    }
}
