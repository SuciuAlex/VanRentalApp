package com.example.VanRentalApp.repository;

import com.example.VanRentalApp.model.Van;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VanRepository extends MongoRepository<Van, String> {

}
