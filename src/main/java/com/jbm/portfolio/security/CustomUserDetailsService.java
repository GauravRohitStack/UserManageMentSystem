
//package com.jbm.portfolio.security;
//
//import java.util.Collection;
//import java.util.Collections;
//
//import org.springframework.security.core.*;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.jbm.portfolio.model.User;
//
//@SuppressWarnings("serial")
//public class CustomUserDetailsService implements UserDetailsService{
//
//    private final User user;
//
//    public CustomUserDetailsService(User user) {
//        this.user = user;
//    }
//
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.emptyList();
//    }
//
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    public String getUsername() {
//        return user.getEmail();
//    }
//
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    public boolean isEnabled() {
//        return true;
//    }
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}


package com.jbm.portfolio.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.jbm.portfolio.model.User;
import com.jbm.portfolio.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(user);
    }
}


