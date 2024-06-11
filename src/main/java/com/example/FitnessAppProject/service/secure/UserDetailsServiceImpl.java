package com.example.FitnessAppProject.service.secure;


import com.example.FitnessAppProject.models.entity.RoleEntity;
import com.example.FitnessAppProject.repo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository= userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .getByUsername(username)
                .map(UserDetailsServiceImpl::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
    }

    private static UserDetails map(com.example.FitnessAppProject.models.entity.User userEntity) {
        return User
                .withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(userEntity.getRoles().stream().map(UserDetailsServiceImpl::map).toList())
                .build();
    }

    private static GrantedAuthority map(RoleEntity roleEntity) {
        return new SimpleGrantedAuthority(
                "ROLE_" + roleEntity.getRole().name()
        );
    }
}
