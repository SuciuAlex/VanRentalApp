package com.example.VanRentalApp.service;

import com.example.VanRentalApp.model.ApplicationUser;
import com.example.VanRentalApp.repository.ApplicationUserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.VanRentalApp.config.ApplicationSecurityUserRoleConfig.*;

@Repository("fake")
public class ApplicationUserRepositoryServiceFake implements ApplicationUserRepository {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserRepositoryServiceFake(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        "Alex",
                        passwordEncoder.encode("password"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "Andreea",
                        passwordEncoder.encode("password"),
                        OPERATOR.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "Bogdan",
                        passwordEncoder.encode("password"),
                        USER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;
    }

}
