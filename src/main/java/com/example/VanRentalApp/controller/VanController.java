package com.example.VanRentalApp.controller;

import com.example.VanRentalApp.model.Van;
import com.example.VanRentalApp.service.VanService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vans")
@AllArgsConstructor
public class VanController {

    private final VanService vanService;

    @GetMapping(path = "{licensePlate}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_OPERATOR','ROLE_USER')")
    public List<Van> getVan(@PathVariable("licensePlate") String licensePlate){
        System.out.println("Getting Van with license plate" + licensePlate);
        if(licensePlate.equals("all")){
            return vanService.getVans();
        }else{
            return vanService.getVan(licensePlate);
        }
    }


    @PostMapping(path = "/admin/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void addVan(@RequestBody List<Van> van){
        System.out.println("AddingNewVan");
        vanService.addVan(van);
    }


    @DeleteMapping(path = "/admin/remove/{licensePlate}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void removeVan(@PathVariable("licensePlate") String licensePlate){
        System.out.println("Removing license plate "+ licensePlate);

        if(licensePlate.equals("all")){
            vanService.removeVans();
        }else{
            vanService.removeVan(licensePlate);
        }
    }

    @PutMapping(path = "/operator/setAvailable/{licensePlate}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_OPERATOR')")
    public void updateVanAvailable(@PathVariable("licensePlate") String licensePlate){
        vanService.updateAvailability(licensePlate,true);
    }

    @PutMapping(path = "/operator/setUnavailable/{licensePlate}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_OPERATOR')")
    public void updateVanUnavailable(@PathVariable("licensePlate") String licensePlate){
        vanService.updateAvailability(licensePlate,false);
    }
}