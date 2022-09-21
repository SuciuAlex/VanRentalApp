package com.example.VanRentalApp.config;
import com.example.VanRentalApp.config.ApplicationSecurityUserPermissionConfig;
import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.VanRentalApp.config.ApplicationSecurityUserPermissionConfig.*;


public enum ApplicationSecurityUserRoleConfig {
    USER(Sets.newHashSet(READ)),

    OPERATOR(Sets.newHashSet(UPDATE)),
    ADMIN(Sets.newHashSet(WRITE,DELETE));

    private final Set<ApplicationSecurityUserPermissionConfig> permissions; //this injects the permissions(all of them) from the ApplicationUserPermission enum
    ApplicationSecurityUserRoleConfig(Set<ApplicationSecurityUserPermissionConfig> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationSecurityUserPermissionConfig> getPermissions() {
        return permissions;
    }

    //used only if the  access model is granted based on the user permissions.
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
        return permissions;
    }


}
