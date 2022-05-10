package com.dev.wackr.Config;
 
import com.dev.wackr.Tables.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 
public class WackrUserDetails implements UserDetails {
 
    private User user;
     
    public WackrUserDetails(User user) {
        this.user = user;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        switch(user.getRole()){
            case "Admin":
                authorities.add(new SimpleGrantedAuthority("Admin"));
            case "User":
                authorities.add(new SimpleGrantedAuthority("User"));
            break;

        }
        //SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

        return authorities;
    }
 
    @Override
    public String getPassword() {
        return user.getPassword();
    }
 
    @Override
    public String getUsername() {
        return user.getEmail();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
 
}