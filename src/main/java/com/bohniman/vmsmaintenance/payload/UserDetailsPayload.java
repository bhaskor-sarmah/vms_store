package com.bohniman.vmsmaintenance.payload;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Column;

import com.bohniman.vmsmaintenance.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * UserDetailsPayload
 */
public class UserDetailsPayload implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
	private String name;
	private String mobile;
	private String email;
    private boolean isEnabled = true;
    
    private List<GrantedAuthority> authorities;

    public UserDetailsPayload(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.isEnabled = user.isEnabled();
        this.authorities = user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


    public UserDetailsPayload() {
    }


    public UserDetailsPayload(String username, String password, String name, String mobile, String email, boolean isEnabled, List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.isEnabled = isEnabled;
        this.authorities = authorities;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsEnabled() {
        return this.isEnabled;
    }

    public boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public UserDetailsPayload username(String username) {
        this.username = username;
        return this;
    }

    public UserDetailsPayload password(String password) {
        this.password = password;
        return this;
    }

    public UserDetailsPayload name(String name) {
        this.name = name;
        return this;
    }

    public UserDetailsPayload mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public UserDetailsPayload email(String email) {
        this.email = email;
        return this;
    }

    public UserDetailsPayload authorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDetailsPayload)) {
            return false;
        }
        UserDetailsPayload userDetailsPayload = (UserDetailsPayload) o;
        return Objects.equals(username, userDetailsPayload.username) && Objects.equals(password, userDetailsPayload.password) && Objects.equals(name, userDetailsPayload.name) && Objects.equals(mobile, userDetailsPayload.mobile) && Objects.equals(email, userDetailsPayload.email) && isEnabled == userDetailsPayload.isEnabled && Objects.equals(authorities, userDetailsPayload.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, name, mobile, email, isEnabled, authorities);
    }

    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", name='" + getName() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", email='" + getEmail() + "'" +
            ", isEnabled='" + isIsEnabled() + "'" +
            ", authorities='" + getAuthorities() + "'" +
            "}";
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    
}