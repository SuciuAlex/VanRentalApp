package com.example.VanRentalApp.repository;

import com.example.VanRentalApp.model.ApplicationUser;

import java.util.Optional;

public interface ApplicationUserRepository {

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);

}
