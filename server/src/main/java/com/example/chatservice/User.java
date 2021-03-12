package com.example.chatservice;

import com.example.chatservice.authentication.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Table("User")
@ToString @NoArgsConstructor
public class User implements UserDetails {

    @Id
    private Long id;

    private String username;
    private String password;

    public User (String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public User (String username) {
        this.username = username;
    }

    @Transient
    @Getter @Setter
    private List<Role> roles;

    @Getter @Setter
    private String nickname;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(Role.ROLE_USER).stream().map(authority -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() { return true; }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
