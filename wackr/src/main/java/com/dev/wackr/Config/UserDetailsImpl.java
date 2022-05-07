package com.dev.wackr.Config;
 
import com.dev.wackr.Repos.*;
import com.dev.wackr.Tables.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 
public class UserDetailsImpl implements UserDetailsService {
 
    @Autowired
    private UserRepo userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
            User user = userRepository.getUserByEmail(email);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new WackrUserDetails(user);
    }
 
}